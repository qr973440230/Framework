package com.qr.core.framework.mvvm.model.repository.entity;

import android.os.AsyncTask;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.qr.core.framework.configration.retrofit.APIResponse;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class NetworkBoundResource<T> {
    private final MediatorLiveData<Resource<T>> result = new MediatorLiveData<>();

    @MainThread
    public NetworkBoundResource(){
        result.setValue(Resource.loading());
        LiveData<T> localDataSource = localDataSource();
        result.addSource(localDataSource,localData->{
            result.removeSource(localDataSource);
            if(shouldFetch(localData)){
                fetchFromNetwork(localDataSource);
            }else{
                result.addSource(localDataSource,newLocalData->{
                    result.setValue(Resource.success(newLocalData));
                });
            }
        });
    }

    @MainThread
    private void fetchFromNetwork(LiveData<T> localDataSource){
        LiveData<APIResponse<T>> networkDataSource = networkDataSource();
        result.addSource(localDataSource,localData->{
            result.setValue(Resource.loading(localData));
        });
        result.addSource(networkDataSource,networkData->{
            result.removeSource(networkDataSource);
            result.removeSource(localDataSource);
            if(networkData.isSuccessful()){
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        saveNetworkData(networkData.body);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        result.addSource(localDataSource,localData->{
                            result.setValue(Resource.success(localData));
                        });
                    }
                }.execute();
            }else{
                onFetchFailed();
                result.addSource(localDataSource,localData->{
                    result.setValue(Resource.error(networkData.errorMessage,localData));
                });
            }
        });
    }

    // fetchFail
    protected void onFetchFailed(){
    }

    // 本地数据源
    protected abstract LiveData<T> localDataSource();
    // 网络数据源
    protected abstract LiveData<APIResponse<T>> networkDataSource();
    // 是否应该从网络上更新
    protected abstract boolean shouldFetch(T localData);
    // 保存网络数据
    protected abstract void saveNetworkData(@NonNull T data);

    protected abstract Observable<T> dbSource();
    public Observable<Resource<T>> getObservable(){
        return Observable.<Resource<T>>create(emitter -> {
            emitter.onNext(Resource.loading());
            Observable<T> dbSource = dbSource();
        });
    }
}
