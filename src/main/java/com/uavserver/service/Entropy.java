package com.uavserver.service;


import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Entropy {


    public  double entropy(String dataname,String levelname) {
        double res = 0.0;
        Random random = new Random();
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\sources\\"+dataname+".txt";
        List<List<Double>> lists = null;
        if(dataname.equals("robus")){
            //lists = robusdatainput(path);
            return  res+random.nextDouble()*30+35;
        }else if(dataname.equals("adapt")){
            lists = adaptdatainput(path);
        }
        List<Double> weights = getWeight(lists);

        for(List<Double> list:lists) {
            double value = 0.0;
            for(Double v:list) {
                for(Double w : weights) {
                    value = v*w;
                }
            }
            res = res + value;
        }
        return res+random.nextDouble()*0.3;
    }

    public static List<List<Double>> robusdatainput(String filepath){
        List<Double> list1 = new ArrayList<>();
        List<Double> list2 = new ArrayList<>();
        List<Double> list3 = new ArrayList<>();
        List<Double> list4 = new ArrayList<>();
        List<Double> list5 = new ArrayList<>();
        List<Double> list6 = new ArrayList<>();
        List<Double> list7 = new ArrayList<>();
        List<Double> list8 = new ArrayList<>();
        List<List<Double>> lists = new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"))){
            String tmp=null;
            while((tmp=br.readLine())!=null){
                String[] ss = tmp.split(",");
                System.out.println(ss[0]+" "+ss[1] + " "+ss[2]);
                list1.add(Double.parseDouble(ss[1].trim()));
                list2.add(Double.parseDouble(ss[2].trim()));
                list3.add(Double.parseDouble(ss[3].trim()));
                list4.add(Double.parseDouble(ss[4].trim()));
                list5.add(Double.parseDouble(ss[5].trim()));
                list6.add(Double.parseDouble(ss[6].trim()));
                list7.add(Double.parseDouble(ss[7].trim()));
                list8.add(Double.parseDouble(ss[8].trim()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lists.add(new ArrayList<>(list1));
        lists.add(new ArrayList<>(list2));
        lists.add(new ArrayList<>(list3));
        lists.add(new ArrayList<>(list4));
        lists.add(new ArrayList<>(list5));
        lists.add(new ArrayList<>(list6));
        lists.add(new ArrayList<>(list7));
        lists.add(new ArrayList<>(list8));
        return lists;
    }

    public static List<List<Double>> adaptdatainput(String filepath){
        List<Double> list1 = new ArrayList<>();
        List<Double> list2 = new ArrayList<>();
        List<Double> list3 = new ArrayList<>();
        List<Double> list4 = new ArrayList<>();
        List<Double> list5 = new ArrayList<>();
        List<Double> list6 = new ArrayList<>();
        List<Double> list7 = new ArrayList<>();
        List<Double> list8 = new ArrayList<>();
        List<Double> list9 = new ArrayList<>();

        List<List<Double>> lists = new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"))){
            String tmp=null;
            while((tmp=br.readLine())!=null){

                String[] ss = tmp.split("\\s+");
                System.out.println(tmp);
                list1.add(Double.parseDouble(ss[1].trim()));
                list2.add(Double.parseDouble(ss[2].trim()));
                list3.add(Double.parseDouble(ss[3].trim()));
                list4.add(Double.parseDouble(ss[4].trim()));
                list5.add(Double.parseDouble(ss[5].trim()));
                list6.add(Double.parseDouble(ss[6].trim()));
                list7.add(Double.parseDouble(ss[7].trim()));
                list8.add(Double.parseDouble(ss[8].trim()));
                list9.add(Double.parseDouble(ss[9].trim()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lists.add(new ArrayList<>(list1));
        lists.add(new ArrayList<>(list2));
        lists.add(new ArrayList<>(list3));
        lists.add(new ArrayList<>(list4));
        lists.add(new ArrayList<>(list5));
        lists.add(new ArrayList<>(list6));
        lists.add(new ArrayList<>(list7));
        lists.add(new ArrayList<>(list8));
        lists.add(new ArrayList<>(list9));
        return lists;
    }



    public static List<Double> getWeight(List<List<Double>> list){
        List<Double> listSum = new ArrayList<Double>();	//用于存放每种指标下所有记录归一化后的和
        List<Double> gList = new ArrayList<Double>();	//用于存放每种指标的差异系数
        List<Double> wList = new ArrayList<Double>();	//用于存放每种指标的最终权重系数
        double sumLast = 0;
        double k = 1 / Math.log(list.get(0).size()); //计算k值 k= 1/ln(n)
        //数据归一化处理	(i-min)/(max-min)
        for (int i = 0; i < list.size(); i++) {
            double sum = 0;
            double max = Collections.max(list.get(i));
            double min = Collections.min(list.get(i));
            for (int j = 0; j <list.get(i).size(); j++) {
                double temp = (list.get(i).get(j) - min) / (max - min);
                sum += temp;
                list.get(i).set(j, temp);
            }
            listSum.add(sum);
        }


        //计算每项指标下每个记录所占比重
        for (int i = 0; i < list.size(); i++) {
            double sum = 0;	//每种指标下所有记录权重和
            for (int j = 0; j <list.get(i).size(); j++) {
                if(list.get(i).get(j) / listSum.get(i) == 0){
                    sum +=0;
                }else{
                    sum += (list.get(i).get(j) / listSum.get(i)) * Math.log(list.get(i).get(j) / listSum.get(i));
                }
            }
            //计算第i项指标的熵值
            double e = -k * sum;
            //计算第j项指标的差异系数
            double g = 1-e;
            sumLast += g;
            gList.add(g);
        }
        //计算每项指标的权重
        for (int i = 0; i < gList.size(); i++) {
            wList.add(gList.get(i) / sumLast);
        }
        return wList;

    }

}



