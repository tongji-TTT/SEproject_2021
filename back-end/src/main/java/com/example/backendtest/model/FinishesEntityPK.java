package com.example.backendtest.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FinishesEntityPK implements Serializable {
    private int taskId;
    private int studentId;

    @Column(name = "task_id")
    @Id
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Column(name = "student_id")
    @Id
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FinishesEntityPK that = (FinishesEntityPK) o;

        if (taskId != that.taskId) return false;
        if (studentId != that.studentId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + studentId;
        return result;
    }
}
