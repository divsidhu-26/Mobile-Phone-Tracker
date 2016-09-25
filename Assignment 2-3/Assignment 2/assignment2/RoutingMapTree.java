import java.io.*;
import java.util.*;
public class RoutingMapTree{
	public Exchange root;
	public RoutingMapTree() {
		root=new Exchange(0);
	}
	public RoutingMapTree(int a){
		root=new Exchange(a);
	}
	public void switchOn(MobilePhone a, Exchange b){
		if(a.status == 0){
			a.switchOn();
			b.AddMobilePhone(a);
			Exchange c = b;
			do{
				c=c.parent;
				c.set.Insert(a);
			}while(c!=root);
		}
	}
	public void switchOff(MobilePhone a){
		if(a.status ==1){
			a.switchOff();
		}
	}
	public Exchange findExchange(int a,RoutingMapTree b,Boolean bool){
		if(b.root == null){
			return null;
		}
		Exchange c = b.root;
		Exchange d=null;
		if(c.number == a){
			bool=true;
			return c;
		}
		for(int i=0;i<c.numofChildren() && bool == false;i++){
			RoutingMapTree f = c.subtree(i);
			d=findExchange(a,f,bool);
			if(d!=null){
				bool = true;
				return d;
			}
		}
		return null;
	}
	public MobilePhone findMobilePhone(int a, RoutingMapTree b){
		MobilePhoneSet rootset = b.root.residentSet();
		Iterator<MobilePhone> rst = rootset.list.iterator();
		int i=0;
		while(rst.hasNext()){
			MobilePhone temp = rst.next();
			if(temp.Phonenumber == a)
			{
				break;
			}
			i++;
		}
		if(i<rootset.list.size())
			return rootset.list.get(i);
		else{
						try{
						throwThree();
						return null;
					}catch(IllegalAccessException e){
						System.out.println(e);
						return null;
					}
					}
		
	}
	static void throwOne() throws IllegalAccessException {
		throw new IllegalAccessException("No Exchange a found");
	}
	static void throwTwo() throws IllegalAccessException {
		throw new IllegalAccessException("No Exchange b found");
	}
	static void throwThree() throws IllegalAccessException {
		throw new IllegalAccessException("No Mobile b found");
	}
	static void throwFour() throws IllegalAccessException {
		throw new IllegalAccessException("No bth child found");
	}
	public void performAction(String actionMessage){
		StringTokenizer st = new StringTokenizer(actionMessage);
		String method = st.nextToken();
	try{	
			if(method.equals("addExchange")){
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				Exchange e = findExchange(a,this,false);
				Exchange d = new Exchange(b);
				if(e!=null){
					e.AddChild(d);
				}
				else{
					try{
						throwOne();
					}catch(IllegalAccessException p){
						System.out.println(p);
					}
				}
			
		}
		else{
			if(method.equals("switchOnMobile")){
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(findExchange(b,this,false)!=null){
					Exchange d = findExchange(b,this,false);
					MobilePhone e = new MobilePhone(a);
					switchOn(e,d); 
				}
				else{
					try{
						throwTwo();
					}catch(IllegalAccessException e){
						System.out.println(e);
					}
				}
			}
			else{
				if(method.equals("switchOffMobile")){
					int a = Integer.parseInt(st.nextToken());
					MobilePhone d = findMobilePhone(a,this);
					if(d!=null){
						switchOff(d);
					}	
				}
				else{
					if(method.equals("queryNthChild")){
						int a = Integer.parseInt(st.nextToken());
						int b = Integer.parseInt(st.nextToken());
						Exchange d = findExchange(a,this,false);
						if(d.numofChildren()>b){
							System.out.print("queryNthChild "+ a + " " + b + ": ");
							System.out.println(d.child(b).number);
						}
						else{
							try{
								throwFour();
							}catch(IllegalAccessException p){
								System.out.println(p);
							}
						}
					}
					else{
						if(method.equals("queryMobilePhoneSet")){
							int a = Integer.parseInt(st.nextToken());
							Exchange d = findExchange(a,this,false);
							if(d!=null){
								MobilePhoneSet e = d.residentSet();
								System.out.print("queryMobilePhoneSet "+ a +": ");
								e.printset();
							}
							else{
								try{
									throwOne();
								}catch(IllegalAccessException p){
									System.out.println(p);
								}
							}
						}
					}
				}
			}
		}
	}catch(Exception e){e.printStackTrace();}
	}
}

class MobilePhone{
	int Phonenumber;
	int status=0;
	public Exchange location;
	MobilePhone(int number){
		Phonenumber = number;
	}
	public int number(){
		return Phonenumber;
	}
	public Boolean status(){
		if(status==0)
			return false;
		else return true;
	}
	public void switchOn(){
		status = 1;
	}
	public void switchOff(){
		status = 0;
		location = null;
	}
	static void throwOne() throws IllegalAccessException {
		throw new IllegalAccessException("Phone Switched off ");
	}		
	public Exchange location(){
		if(status == 0)
			try{
				throwOne();
				return location;
			}catch(IllegalAccessException e){
				System.out.println(e);
				return location;
			}
		else{
			return location;
		}	
	}      					
}

class MobilePhoneSet{
	LinkedList<MobilePhone> list = new LinkedList<MobilePhone>();
	public void Insert(MobilePhone num){
		list.add(num);
	}
	public void Delete(MobilePhone num){
		list.remove(num);
	}
	public Boolean IsEmpty(){
		if(list.size() == 0)
			return true;
		else return false;
	}
	public Boolean IsMember(MobilePhone num){
		return list.contains(num);
	}
	Iterator<MobilePhone> llist = list.iterator();
	Iterator<MobilePhone> llist1 = list.iterator();
	public void printset(){
		for(int i=0;i<list.size();i++){
			if(list.get(i).status ==1){
			System.out.print((list.get(i)).Phonenumber);
			if(i!=(list.size()-1)){
				System.out.print(", ");
			}
		}
	}
		System.out.println();
	}
	public MobilePhoneSet Union(MobilePhoneSet a){
		Iterator<MobilePhone> la = (a.list).iterator();
		MobilePhoneSet b = new MobilePhoneSet();
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
	public MobilePhoneSet Intersection(MobilePhoneSet a){
		MobilePhoneSet b = new MobilePhoneSet();
		while(llist.hasNext()){
			if((a.list).contains(llist1.next())){
				b.Insert(llist.next());
			}
			else llist.next();
		}
		return b;
	}
}

class Exchange{
	int number;
	public int size=0;
	Exchange parent=null;
	MobilePhoneSet set;
	Exchange[] children = new Exchange[100];
	Exchange(int num){
		number = num;
		set=new MobilePhoneSet();
	}
	public Exchange parent(){
		return parent;
	}
	public int numofChildren(){
		return size;
	} 
	public Exchange child(int i){
		return children[i];
	}
	public Boolean IsRoot(){
		if(parent == null)
			return true;
		return false;
	}
	public void AddChild(Exchange a){
		children[size] = a;
		size++;
		a.parent=this;							
	}
	public RoutingMapTree subtree(int i){
		RoutingMapTree c = new RoutingMapTree();
		c.root = this.child(i);
		return c;										//to be done
	}
	public void AddMobilePhone(MobilePhone a){
		set.Insert(a);
		a.location=this;
	}
	public MobilePhoneSet residentSet(){
		return set;
	}

}
class ExchangeList{
	public void addExchange(Exchange a,Exchange b){
		a.AddChild(b);
	}
}

	

