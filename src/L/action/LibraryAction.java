package L.action;
import java.sql.*;
import java.sql.Connection;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.text.*;
import java.sql.DriverManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class LibraryAction extends ActionSupport {
		public float price;
		public String ISBN;
		public String name;
		public int Age;
		public String Country;
		private String title;
		public String AuthorID;
		public String Publisher;
		public String PublishDate;
		private Map m;
		public ArrayList<String> list = new ArrayList<String>();
		Statement stmt = null;
		Connection conn = null;
		
		public void setAge(int Age)
		{
			this.Age=Age;
		}
		
		public void setCountry(String Country)
		{
			this.Country = Country;
		}
		
		public void setISBN(String ISBN)
		{
			this.ISBN = ISBN;
		}
		
		public String getISBN()
		{
			return ISBN;
		}
		
		public void setprice(float price)
		{
			this.price = price;
		}

		public float getprice()
		{
			return price;
		}
		
		public void setPublisher(String Publisher)
		{
			this.Publisher=Publisher;
		}
		
		public void setPublishDate(String PublishDate)
		{
			this.PublishDate=PublishDate;
		}
		
	
		public Map getM()
		{
			return m;
		}
		
		public void setM(Map m)
		{
			this.m = m;
		}
	
		public String getTitle()
		{
			return title;
		}
		
		public void setTitle(String title)
		{
			this.title =title;
		}
		
		public String getName()
		{
			return name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		
		public void connec()
		{
		datebase();
		try{
			stmt=conn.createStatement();
			String sql = "select * from author where Name = '"+ name +"';";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				AuthorID = rs.getString(1);
			}
			Statement stmt1 = conn.createStatement();
			String sql1 = "select * from book where AuthorID ='" +AuthorID +"';";
			ResultSet qs = stmt1.executeQuery(sql1);
			while(qs.next())
			{
				list.add(qs.getString(2));
			}
			stmt.close();
			stmt1.close();
		}
		catch(Exception se){
			se.printStackTrace();
		}
		
	}
		
//	public String allbook()
//	{
//		datebase();
//		try{
//			stmt=conn.createStatement();
//			String sql="select * from book";
//			ResultSet rs=stmt.executeQuery(sql);
//			while(rs.next())
//			{
//				list.add(rs.getString(2));
//			}
//			return "showall";
//		}
//		catch(Exception e)
//		{
//			System.out.println("ad");
//			e.printStackTrace();
//			return "showall";
//		}
//	}
	
	public String delete()
	{
		datebase();
		try{
			stmt=conn.createStatement();
			String sql1="select * from book where title='" +title+"';";
			ResultSet qs = stmt.executeQuery(sql1);
			while(qs.next())
			{
				AuthorID = qs.getString(3);
			}
			String sql="delete from book where title='" + title +"';";
			int i=stmt.executeUpdate(sql);
			String sql2="select * from author where authorID='" +AuthorID+"';";
			ResultSet rs = stmt.executeQuery(sql2);
			while(qs.next())
			{
				name = rs.getString(2);
			}
			stmt.close();
		}
		catch(Exception se){
			se.printStackTrace();
		}
		connec();
		System.out.println(name);
		return "input";
	}
	
	public String detail()
	{
		String testname = "";
		datebase();
		try{
			stmt=conn.createStatement();
			String sql = "select * from book where title = '"+ title +"';";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Success loading mysql drivers!");
			while(rs.next())
			{
				
				ISBN = rs.getString(1);
				AuthorID=rs.getString(3);
				Publisher = rs.getString(4);
				PublishDate = rs.getString(5); 
				price = rs.getFloat(6);
			}
			System.out.println(name);
			Statement stmt2=conn.createStatement();
			String sql1="select * from author where authorID='"+AuthorID+"';";
			ResultSet ps = stmt2.executeQuery(sql1);
			while(ps.next())
			{
				testname = ps.getString(2);
				Age = ps.getInt(3);
				Country = ps.getString(4);
				System.out.println(ps.getString(2)+ps.getInt(3)+ps.getString(4));
			}
		}
			catch(Exception se){
				System.out.println(se.getMessage());//printStackTrace()
			}
		System.out.println(testname);
			m=new HashMap();
			m.put("title", title);
			m.put("ISBN",ISBN);
			m.put("Publisher",Publisher);
			m.put("Publishdate",PublishDate);
			m.put("price",price);
			m.put("Name",testname);
			m.put("Age",Age);
			m.put("Country",Country);
		return "error";
	}
				
	public String seraching() throws Exception{
		connec();
		m = ActionContext.getContext().getSession();
		for(int i= 0;i<list.size();i++)
		{
			m.put("title", list.get(i));
		}
		return "success";
	}
	
	
	
	public void datebase()
	{
		String driver = "com.mysql.jdbc.Driver";
//		String username = System.getenv("root");
//		String password = System.getenv("stefan1007");
//		String dbUrl = String.format("jdbc:mysql://%s:%s/%s", System.getenv("localhost"), System.getenv("3306"), System.getenv("library"));
		try {
		    Class.forName(driver).newInstance();
		    conn = DriverManager.getConnection("jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_stefansun","1zjwm2n43l","4y5hml1203liim40k3hhjwiiij11mm3kkx0kki4h");
		    // ...
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.print("fail:"+e.getMessage());
		}
}

	
}


