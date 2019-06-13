package com.qr.core.framework.configration.network;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.schedulers.Schedulers;

public abstract class NetworkBoundResource<ResultType,RequestType> {
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    public NetworkBoundResource(){
        result.setValue(Resource.loading(null));
        LiveData<ResultType> dbDataSource = dbDataSource();
        result.addSource(dbDataSource,dbData->{
            result.removeSource(dbDataSource);
            if(shouldFetch(dbData)){
                setValue(Resource.loading(dbData));
                fetchNetworkDataSource(dbDataSource);
            }else{
                result.addSource(dbDataSource,newDbData->{
                    setValue(Resource.success(newDbData));
                });
            }
        });
    }

    private void fetchNetworkDataSource(final LiveData<ResultType> dbDataSource){
        LiveData<NetworkResponse<RequestType>> networkDataSource = LiveDataReactiveStreams.fromPublisher(networkDataSource());
        result.addSource(networkDataSource,networkData->{
            result.removeSource(networkDataSource);
            if(networkData.isSuccessful()){
                Schedulers.io().scheduleDirect(()->{
                   handleNetworkResult(networkData.body);
                    AndroidSchedulers.mainThread().scheduleDirect(()->{
                       result.addSource(dbDataSource(),dbData->{
                            setValue(Resource.success(dbData));
                       });
                    });
                });
            }else{
                onFetchFailed();
                result.addSource(dbDataSource,dbData->{
                    setValue(Resource.error(dbData,networkData.errorMessage));
                });
            }
        });
    }
    private void setValue(Resource<ResultType> newValue){
        if(!ObjectHelper.equals(result.getValue(),newValue)){
            result.setValue(newValue);
        }
    }
    protected void onFetchFailed(){
    }
    protected abstract void handleNetworkResult(RequestType networkData);
    protected abstract boolean shouldFetch(@Nullable ResultType localData);
    protected abstract LiveData<ResultType> dbDataSource();
    protected abstract Flowable<NetworkResponse<RequestType>> networkDataSource();

    public LiveData<Resource<ResultType>> getLiveData(){
        return result;
    }
}
