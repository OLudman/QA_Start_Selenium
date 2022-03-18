package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProvider {
    @DataProvider
    public Iterator<Object[]> loginValidData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"olgaludman@gmail.com", "mK#J7a#mDU6.9Gz"});
        list.add(new Object[]{"olgaludman@gmail.com", "mK#J7a#mDU6.9Gz"});
        list.add(new Object[]{"olgaludman@gmail.com", "mK#J7a#mDU6.9Gz"});
        return list.iterator();
    }

    public Iterator<Object[]> loginValidDataUser(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withEmail("olgaludman@gmail.com").withPassword("mK#J7a#mDU6.9Gz")});
        list.add(new Object[]{new User().withEmail("olgaludman@gmail.com").withPassword("mK#J7a#mDU6.9Gz")});
        list.add(new Object[]{new User().withEmail("olgaludman@gmail.com").withPassword("mK#J7a#mDU6.9Gz")});
        return list.iterator();
    }

    public Iterator<Object[]> loginValidDataBoard(){
        List<Object[]> list = new ArrayList<>();
        return list.iterator();
    }

    // not all
}
