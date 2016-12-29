package deerlight.com.accountinglibrary.access.write;


/**
 * Created by taaze on 2016/12/29.
 */

public interface DataWrite {

    void Insert(String stuff);

    void Update(String OldStuff, String NewStuff);

    void Remove(String stuff);

    void OpenDB();

    void CloseDB();

    void showToast(String message);
}
