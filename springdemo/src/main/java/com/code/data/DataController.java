package com.code.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.swing.plaf.windows.ClassicSortArrowIcon;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2017/10/5.
 * 数据库操作 demo
 */
@RestController
public class DataController {

    @Autowired
    private PeopleDao peopleDao;

//     final  PeopleEntity  x ;

    static {
        System.out.println("static静态初始化块");
    }


    {
        System.out.println("初始化块");
    }

    public DataController() {

    }


    /**
     * 查询列表
     * @return
     */
    @GetMapping(value = "/peoples")
    public List<PeopleEntity> peoples (){
        return peopleDao.findAll();
    }

    /**
     * 添加
     * @return
     */
    @PostMapping("/peoples")
    public PeopleEntity addPeople(@RequestParam  String name , @RequestParam Integer age){
        PeopleEntity peopleEntity=new PeopleEntity();
        peopleEntity.setName(name);
        peopleEntity.setAge(age);
        return peopleDao.save(peopleEntity);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @GetMapping("/peoples/{id}")
    public PeopleEntity findOne( @PathVariable Integer id){
        return peopleDao.findOne(id);
    }

    /**
     * 根据age查询
     * @param age
     * @return
     */
    @GetMapping("/peoples/age/{age}")
    public List<PeopleEntity> findByAge(@PathVariable Integer age){
        return peopleDao.findByAge(age);
    }

    /**
     * 更新
     * @param id
     * @param name
     * @param age
     * @return
     */
    @PutMapping("/peoples/{id}")
    public PeopleEntity update( @PathVariable Integer id,@RequestParam  String name , @RequestParam Integer age){
        PeopleEntity peopleEntity=new PeopleEntity();
        peopleEntity.setId(id);
        peopleEntity.setName(name);
        peopleEntity.setAge(age);
        Integer x=0;
        return peopleDao.save(peopleEntity);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/peoples/{id}")
    public void delete( @PathVariable Integer id){
        peopleDao.delete(id);
    }

    public static void main(String[] args) throws ClassNotFoundException {
//        objPoolTest();
//        Class.forName("com.code.data.DataController");
//        new DataController();
//        new TreeMap();
//        new ConcurrentHashMap<String,String>();

        int score[] = {67, 69, 75, 87, 89, 90, 99, 100};
        for(int i=0;i<score.length;i++){
            for(int j=0;j<score.length-i-1;j++){
                if(score[j]<score[j+1]){
                    int temp=score[j];
                    score[j]=score[j+1];
                    score[j+1]=temp;
                }
            }
        }

        System.out.print("最终排序结果：");
        for(int a = 0; a < score.length; a++){
            System.out.print(score[a] + "\t");
        }


    }

    public static void objPoolTest() {
        int i = 40;
        int i0 = 40;
        Integer i1 = 40;
        Integer i2 = 40;
        Integer i3 = 0;
        Integer i4 = new Integer(40);
        Integer i5 = new Integer(40);
        Integer i6 = new Integer(0);
        Double d1=1.0;
        Double d2=1.0;

        System.out.println("i=i0\t" + (i == i0));
        System.out.println("i1=i2\t" + (i1 == i2));
        System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));
        System.out.println("i4=i5\t" + (i4 == i5));
        System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));
        System.out.println("d1=d2\t" + (d1==d2));

        System.out.println();
    }
}
