package com.rakibofc.recyclerflow;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

    public static final int ITEM_SIZE = 1000;

    private final MutableLiveData<List<String>> listLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        listLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getListLiveData() {
        return listLiveData;
    }

    public void loadMoreListData(int page, int itemsPerPage) {
        int startIndex = (page - 1) * itemsPerPage;
        int endIndex = startIndex + itemsPerPage;
        List<String> newData = getPartialListData(startIndex, endIndex);
        listLiveData.postValue(newData);
    }

    private List<String> getPartialListData(int startIndex, int endIndex) {
        Context context = getApplication().getApplicationContext();
        List<String> listData = new ArrayList<>();

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (i <= ITEM_SIZE) {
                listData.add(String.format(context.getString(R.string.dummy_list_item), i));
            } else {
                break; // Stop adding data when exceeding ITEM_SIZE
            }
        }
        return listData;
    }
}