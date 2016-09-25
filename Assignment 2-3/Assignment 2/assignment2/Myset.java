import java.io.*;
import java.util.*;
public class Myset1{
	LinkedList<Object> list = new LinkedList<Object>();
	public void Insert(Object o){
		list.add(o);
	}
	public void Delete(Object o){
		list.remove(o);
	}
	public Boolean IsEmpty(){
		if(list.size() == 0)
			return true;
		else return false;
	}
	public Boolean IsMember(Object o){
		return list.contains(o);
	}
	Iterator<Object> llist = list.iterator();
	Iterator<Object> llist1 = list.iterator();
	public Myset1 Union(Myset1 a){
		Iterator<Object> la = a.list.iterator();
		Myset1 b = new Myset1();
		while(la.hasNext()){
			b.Insert(llist.next());
		}
		while(llist.hasNext() && llist1.hasNext()){
			if(!(b.list).contains(llist1.next())){
				b.Insert(llist.next());
			}
			else llist.next();
		}
		return b;
	}
	public Myset1 Intersection(Myset1 a){
		Myset1 b = new Myset1();
		while(llist.hasNext()){
			if((a.list).contains(llist1.next())){
				b.Insert(llist.next());
			}
			else llist.next();
		}
		return b;
	}
}