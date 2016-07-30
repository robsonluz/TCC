/*
 * Created on 10/02/2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.agentrj.core.agents.database;
import java.sql.*;
/**
 * @author Adminsitrador
 *
 */
public interface IGenerico
{
	public String getNameTable();
	public void setAttributes(PreparedStatement stm)throws SQLException;
	public void setBD(ResultSet rs)throws SQLException;
}
