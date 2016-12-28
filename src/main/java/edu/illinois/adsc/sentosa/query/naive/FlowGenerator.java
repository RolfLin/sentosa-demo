package edu.illinois.adsc.sentosa.query.naive;

import edu.illinois.adsc.sentosa.query.Interface.Attraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by robert on 28/12/16.
 */
public class FlowGenerator {

    public Map<Integer, Attraction> attractions;

    public FlowGenerator(Map<Integer, Attraction> attractions) {
        this.attractions = attractions;
    }

    public List<Integer> historyFlow(int id, int year, int month, int day) {
        Random random = new Random(id + year + month + day);
        Attraction attraction = attractions.get(id);
        int start = attraction.startTimeHour * 60 + attraction.startTimeMin;
        int end = attraction.endTimeHour * 60 + attraction.endTimeMin;

        final int peakValue = 1000 + random.nextInt(500);
        final int lowValue = random.nextInt(100);

        final int peakTimeStart = 13 * 60;
        final int peakTimeEnd = 15 * 60;

        List<Integer> ret = new ArrayList<>();
        for(int i = start; i < end; i += 30) {
            Random localRandom = new Random(id + year + month + day + i);
            int value;
            if(i < peakTimeStart) {
                value = lowValue + (int)((double)(i - start) / (peakTimeStart - start) * (peakValue - lowValue));
                value += localRandom.nextInt(500) - 250;
            } else if (i > peakTimeEnd) {
                value = lowValue + (int)((double)(end - i) / (end - peakTimeEnd) * (peakValue - lowValue));
                value += localRandom.nextInt(500) - 250;
            } else {
                value = peakValue + localRandom.nextInt(100) - 50;
            }
            ret.add(Math.max(0, value));
        }

        return ret;
    }

    public List<Integer> predicteFlow(int id, int year, int month, int day, int startHour, int startMin, int nSteps, int step) {
        Random random = new Random(id + year + month + day);
        Attraction attraction = attractions.get(id);
        int start = attraction.startTimeHour * 60 + attraction.startTimeMin;
        int end = attraction.endTimeHour * 60 + attraction.endTimeMin;

        final int peakValue = 1000 + random.nextInt(500);
        final int lowValue = random.nextInt(100);

        final int peakTimeStart = 13 * 60;
        final int peakTimeEnd = 15 * 60;

        List<Integer> ret = new ArrayList<>();
        for(int i = startHour * 60 + startMin; i < startHour * 60 + startMin + nSteps * step; i += step) {
            Random localRandom = new Random(id + year + month + day + i);
            int value;
            if(i < peakTimeStart) {
                value = lowValue + (int)((double)(i - start) / (peakTimeStart - start) * (peakValue - lowValue));
                value += localRandom.nextInt(500) - 250;
            } else if (i > peakTimeEnd) {
                value = lowValue + (int)((double)(end - i) / (end - peakTimeEnd) * (peakValue - lowValue));
                value += localRandom.nextInt(500) - 250;
            } else {
                value = peakValue + localRandom.nextInt(100) - 50;
            }
            ret.add(Math.max(0, value));
        }

        return ret;
    }


    public List<Integer> historyQueuingTime(int id, int year, int month, int day) {
        Random random = new Random(id + year + month + day);
        Attraction attraction = attractions.get(id);
        int start = attraction.startTimeHour * 60 + attraction.startTimeMin;
        int end = attraction.endTimeHour * 60 + attraction.endTimeMin;

        final int peakValue = 1000 + random.nextInt(500);
        final int lowValue = random.nextInt(100);

        final int peakTimeStart = 13 * 60;
        final int peakTimeEnd = 15 * 60;

        final int maxWaitingTime = 45;
        final int minWaitingTime = 5;

        List<Integer> ret = new ArrayList<>();
        for(int i = start; i < end; i += 30) {
            Random localRandom = new Random(id + year + month + day + i);
            int value;
            if(i < peakTimeStart) {
                value = lowValue + (int)((double)(i - start) / (peakTimeStart - start) * (peakValue - lowValue));
                value += localRandom.nextInt(500) - 250;
            } else if (i > peakTimeEnd) {
                value = lowValue + (int)((double)(end - i) / (end - peakTimeEnd) * (peakValue - lowValue));
                value += localRandom.nextInt(500) - 250;
            } else {
                value = peakValue + localRandom.nextInt(100) - 50;
            }
            int waitingTime = minWaitingTime + (int)((value - lowValue) / (double) (peakValue - lowValue) * (maxWaitingTime - minWaitingTime));
            waitingTime += localRandom.nextInt(10) - 5;
            ret.add(Math.max(0, waitingTime));
        }

        return ret;
    }

    public List<Integer> predicateQueuingTime(int id, int year, int month, int day, int startHour, int startMin, int nSteps, int step) {
        Random random = new Random(id + year + month + day);
        Attraction attraction = attractions.get(id);
        int start = attraction.startTimeHour * 60 + attraction.startTimeMin;
        int end = attraction.endTimeHour * 60 + attraction.endTimeMin;

        final int peakValue = 1000 + random.nextInt(500);
        final int lowValue = random.nextInt(100);

        final int peakTimeStart = 13 * 60;
        final int peakTimeEnd = 15 * 60;

        final int maxWaitingTime = 45;
        final int minWaitingTime = 5;

        List<Integer> ret = new ArrayList<>();
        for(int i = startHour * 60 + startMin; i <  startHour * 60 + startMin + nSteps * step; i += step) {
            Random localRandom = new Random(id + year + month + day + i);
            int value;
            if(i < peakTimeStart) {
                value = lowValue + (int)((double)(i - start) / (peakTimeStart - start) * (peakValue - lowValue));
                value += localRandom.nextInt(500) - 250;
            } else if (i > peakTimeEnd) {
                value = lowValue + (int)((double)(end - i) / (end - peakTimeEnd) * (peakValue - lowValue));
                value += localRandom.nextInt(500) - 250;
            } else {
                value = peakValue + localRandom.nextInt(100) - 50;
            }
            int waitingTime = minWaitingTime + (int)((value - lowValue) / (double) (peakValue - lowValue) * (maxWaitingTime - minWaitingTime));
            waitingTime += localRandom.nextInt(10) - 5;
            ret.add(Math.max(0, waitingTime));
        }

        return ret;
    }

    static public void main(String[] args) {
        FlowGenerator flowGenerator = new FlowGenerator(NaiveQueryImpl.instance().attractions);
        System.out.println(flowGenerator.historyFlow(0, 2016, 12, 28));
        System.out.println(flowGenerator.historyFlow(0, 2016, 12, 29));
        System.out.println(flowGenerator.predicteFlow(0, 2016, 12, 29, 15, 0 , 10, 10));
        System.out.println(flowGenerator.predicteFlow(0, 2016, 12, 29, 15, 10 , 10, 10));

        System.out.println(flowGenerator.historyQueuingTime(0, 2016, 12, 29));
        System.out.println(flowGenerator.historyQueuingTime(1, 2016, 12, 29));

        System.out.println(flowGenerator.predicateQueuingTime(0, 2016, 12, 29, 15, 10 , 10, 10));
        System.out.println(flowGenerator.predicateQueuingTime(0, 2016, 12, 29, 15, 20 , 10, 10));
    }
}
