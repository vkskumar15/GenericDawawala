package com.example.genericdawawalauser.modalClass.TimeSlotsModels;

import java.io.Serializable;
import java.util.List;

public class TimeSlotsModelDetails implements Serializable {

    public String date;
    public String start_time;
    public String end_time;
    public String slot_status;
    public List<SlotsModel> slots;

    public String doctor_id;
    public String morning_type;
    public String afternoon_type;
    public String evening_type;
    public List<MorningSlotsModel> morning_slots;
    public List<AfternoonSlotsModel> afternoon_slots;
    public List<EveningSlotsModel> evening_slots;


    public String getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(String doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getMorning_type() {
        return morning_type;
    }

    public void setMorning_type(String morning_type) {
        this.morning_type = morning_type;
    }

    public String getAfternoon_type() {
        return afternoon_type;
    }

    public void setAfternoon_type(String afternoon_type) {
        this.afternoon_type = afternoon_type;
    }

    public String getEvening_type() {
        return evening_type;
    }

    public void setEvening_type(String evening_type) {
        this.evening_type = evening_type;
    }

    public List<MorningSlotsModel> getMorning_slots() {
        return morning_slots;
    }

    public void setMorning_slots(List<MorningSlotsModel> morning_slots) {
        this.morning_slots = morning_slots;
    }

    public List<AfternoonSlotsModel> getAfternoon_slots() {
        return afternoon_slots;
    }

    public void setAfternoon_slots(List<AfternoonSlotsModel> afternoon_slots) {
        this.afternoon_slots = afternoon_slots;
    }

    public List<EveningSlotsModel> getEvening_slots() {
        return evening_slots;
    }

    public void setEvening_slots(List<EveningSlotsModel> evening_slots) {
        this.evening_slots = evening_slots;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSlot_status() {
        return slot_status;
    }

    public void setSlot_status(String slot_status) {
        this.slot_status = slot_status;
    }

    public List<SlotsModel> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotsModel> slots) {
        this.slots = slots;
    }
}
