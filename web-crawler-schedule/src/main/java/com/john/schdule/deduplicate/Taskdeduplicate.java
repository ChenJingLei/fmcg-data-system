package com.john.schdule.deduplicate;

import com.john.crawler.model.Capture;
import com.john.crawler.model.Task;
import com.john.crawler.model.TaskCollection;
import com.john.crawler.model.UserTask;

import java.util.*;

/**
 * Created by cjl20 on 2017/5/23.
 */

public class Taskdeduplicate {

    public TaskCollection taskDeduplicate(List<UserTask> userTasks) {
        TaskCollection taskCollection = new TaskCollection();
        taskCollection.setTimeStamp((new Date().getTime()));
        HashMap<String, HashSet<String>> captureHashMap = new HashMap<>();
        for (UserTask userTask : userTasks) {
            for (Task task : userTask.getGoods()) {
                HashSet<String> goodList = null;
                if (captureHashMap.containsKey(task.getPlatform())) {
                    goodList = captureHashMap.get(task.getPlatform());
                } else {
                    goodList = new HashSet<>();
                }
                if (!goodList.contains(task.getGoodName())) {
                    goodList.add(task.getGoodName());
                    captureHashMap.replace(task.getPlatform(), goodList);
                }
            }
        }
        List<Capture> captureList = new ArrayList<>();
        for (Map.Entry<String, HashSet<String>> entry : captureHashMap.entrySet()) {
            Capture capture = new Capture();
            capture.setPlatform(entry.getKey());
            capture.setGoods(entry.getValue());
            captureList.add(capture);
        }
        taskCollection.setCaptureList(captureList);
        return taskCollection;
    }

}
