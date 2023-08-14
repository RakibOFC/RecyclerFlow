package com.rakibofc.recyclerflow;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private final MutableLiveData<List<String>> listLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);

        listLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getListLiveData() {
        return listLiveData;
    }

    public void loadListData() {
        listLiveData.postValue(getListData());
    }

    private List<String> getListData() {

        Context context = getApplication().getApplicationContext();
        List<String> listData = new ArrayList<>();

        for (int i = 1; i <= MainActivity.ITEM_SIZE; i++) {
            listData.add(String.format(context.getString(R.string.dummy_list_item), i));
        }
        return listData;
    }
}
