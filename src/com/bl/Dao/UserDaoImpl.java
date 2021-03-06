package com.bl.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bl.Config.Configuration;
import com.bl.Model.UserPojo;

public class UserDaoImpl implements IUserDao {

	static Connection con = null;
	PreparedStatement ps = null;
	UserPojo up = new UserPojo();
	String Name;
	String Email;
	String Password;
	String  MobileNumber;

	public boolean getConnection()
	{
		Configuration c = new Configuration();

		con=c.connectionFields();
		if(con!=null)
			return true;
		return false;
	}
	@Override
	public boolean getEmployee(String email, String password) {
		String display = "Select * from Employee";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(display);
			ResultSet resultSet=ps.executeQuery();
			try {
				while(resultSet.next())
				{
					if(resultSet.getString(3).equals(email) && resultSet.getString(4).equals(password))
					{
						return true;
					}
				}
				con.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				con.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
							}
		
		return false;
	}
	@Override
	public boolean insertStudentInformation(UserPojo userDetail) {
		String ins = "insert into Employee(Name,Email,Password,MobileNumber) values(?,?,?,?)";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(ins);
			ps.setString(1,userDetail.getName());
			ps.setString(2,userDetail.getEmail());
			ps.setString(3,userDetail.getPassword());
			ps.setString(4,userDetail.getMobileNumber());
			@SuppressWarnings("unused")
			int resultSet=ps.executeUpdate();
			con.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally
		{
			if(ps!=null)
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//			if(con!=null)
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		}
		return true;
	}

}