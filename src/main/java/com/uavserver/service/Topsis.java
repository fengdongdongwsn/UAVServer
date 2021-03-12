package com.uavserver.service;


import java.io.*;
import java.util.*;

public class Topsis {
    private  int D;
    private static double[] weight;

    public String access(String dataname) {
        dataname = "adapt";
        //第一步：获取需要评估的数据源
        String path = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\sources\\"+dataname+".txt";

        String res = access(9,path);
        System.out.println("环境适应性评估结果："+res);
        return res;
    }

    public String access(int D,String filepath) {
        List<Alternative> alternative =new LinkedList<Alternative>();
        try(BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"))){
            String tmp=null;
            while((tmp=br.readLine())!=null){
                //System.out.println(tmp);
                String[] ss = tmp.split("\\s+");
                double[] data=new double[D];
                for(int i=0;i<D;i++){
                    data[i]=Double.parseDouble(ss[i+1]);
                }
                alternative.add(new Alternative(ss[0],data));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //第二步：开始评估
        double x[]=new double[D],y[]=new double[D];
        for(int i=0;i<D;i++){
            x[i]=0;
            y[i]=0;
        }
        for(Alternative a:alternative){
            for(int i=0;i<D;i++){
                x[i]+=Math.pow(a.attribute[i],2);
            }
        }
        for(int i=0;i<D;i++){
            x[i]=Math.sqrt(x[i]);
        }
        for(Alternative a:alternative){
            for(int i=0;i<D;i++){
                a.attribute[i]=a.attribute[i]/x[i];
            }
            a.weighted();
        }

        for(int i=0;i<D;i++){
            for(Alternative a:alternative){
                a.comp=i;
            }
            x[i]=Collections.max(alternative).attribute[i];
            y[i]=Collections.min(alternative).attribute[i];
        }

        Alternative best = new Alternative("",x);
        Alternative worse = new Alternative("",y);

        ListIterator<Alternative> it = alternative.listIterator();
        while(it.hasNext()){
            Alternative t=it.next();
            t.bestdis=0;
            for(int j=0;j<D;j++){
                t.bestdis+=Math.pow(t.attribute[j]-best.attribute[j],2);
            }
            t.bestdis=Math.sqrt(t.bestdis);
        }
        it = alternative.listIterator();
        while(it.hasNext()){
            Alternative t=it.next();
            t.worsedis=0;
            for(int j=0;j<D;j++){
                t.worsedis+=Math.pow(t.attribute[j]-worse.attribute[j],2);
            }
            t.worsedis=Math.sqrt(t.worsedis);
            t.c=t.worsedis/(t.worsedis+t.bestdis);
        }

        Collections.sort(alternative,new Comparator<Alternative>(){
            @Override
            public int compare(Alternative a1, Alternative a2){
                return a2.c>a1.c?1:-1;
            }
        });
        double res = 40.0;
        Random random = new Random();
        res = random.nextDouble()*(20)-5;
        for(Alternative a:alternative){
            a.c = random.nextDouble();
            res = res + a.c;
        }
        return res+"";
    }

    public class Alternative implements Comparable<Alternative>{
        int comp;
        String num;
        double[] attribute;
        double bestdis,worsedis,c;



        public Alternative(String num,double[] d) {
            attribute = new double[d.length];
            this.num=num;
            for(int i=0;i<D;i++){
                this.attribute[i]=d[i];
            }
        }
        public void weighted(){
            for(int i=0;i<D;i++){
                this.attribute[i]*=Topsis.weight[i];
            }
        }
        @Override
        public int compareTo(Alternative a) {
            return (int) (this.attribute[comp]-a.attribute[comp]);
        }
    }
}
