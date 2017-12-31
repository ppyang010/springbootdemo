package com.code.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/10/5.
 * 数据库操作 demo
 */
@RestController
public class DataController {

    @Autowired
    private UserTestDao userTestDao;

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
    public List<UserTestEntity> peoples (){
        return userTestDao.findAll();
    }

    /**
     * 添加
     * @return
     */
    @PostMapping("/peoples")
    public UserTestEntity addPeople(@RequestParam  String name , @RequestParam Integer age){
        UserTestEntity peopleEntity=new UserTestEntity();
        return userTestDao.save(peopleEntity);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @GetMapping("/peoples/{id}")
    public UserTestEntity findOne( @PathVariable Integer id){
        return userTestDao.findOne(id);
    }

    /**
     * 根据username查询
     * @param username
     * @return
     */
    @GetMapping("/peoples/username/{usernaem}")
    public List<UserTestEntity> findByAge(@PathVariable String username){
        return userTestDao.findByUsername(username);
    }

    /**
     * 更新
     * @param id
     * @param name
     * @param age
     * @return
     */
    @PutMapping("/peoples/{id}")
    public UserTestEntity update( @PathVariable Integer id,@RequestParam  String name , @RequestParam Integer age){
        UserTestEntity peopleEntity=new UserTestEntity();
        peopleEntity.setId(id);
        Integer x=0;
        return userTestDao.save(peopleEntity);
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/peoples/{id}")
    public void delete( @PathVariable Integer id){
        userTestDao.delete(id);
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

//        System.out.println("i=i0\t" + (i == i0));
//        System.out.println("i1=i2\t" + (i1 == i2));
//        System.out.println("i1=i2+i3\t" + (i1 == i2 + i3));
//        System.out.println("i4=i5\t" + (i4 == i5));
//        System.out.println("i4=i5+i6\t" + (i4 == i5 + i6));
//        System.out.println("d1=d2\t" + (d1==d2));

        System.out.println();
    }
}
