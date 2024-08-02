package com.czh.database.search.database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ResultSet解释器
 * @author czh
 * TODO 用Java8函数式方法重构
 */
public class ResultSetParser {
	private final ResultSet resultSet;
	
	public ResultSetParser(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	/**
	 * 解析结果集为一个List, 只读取结果集每一行的第一个元素
	 * @return List
	 */
	public List<String> reduceFirstLine() {
		List<String> res = new ArrayList<>();
		try {
			if (resultSet == null) {
				return res;
			}
			while (resultSet.next()) {
				res.add(resultSet.getString(1));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("reduceFirstLine出错");
			System.exit(0);
		}
		return res;
	}

	/**
	 * 将结果集解析为一个List<List> 结果集的每一行映射成一个List
	 * @return List<List>
	 */
	public List<List<String>> reduceList() {
		List<List<String>> res = new ArrayList<>();
		try {
			if (resultSet == null) {
				return res;
			}
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			while (resultSet.next()) {
				List<String> list = new ArrayList<>(columnCount);
				for (int i = 1; i <= columnCount ; i++) {
					list.add(resultSet.getString(i));
				}
				res.add(list);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("reduceList出错");
		}
		return res;
	}

	/**
	 * 将结果集解析为一个List<Map> 结果集的每一行映射成一个map
	 * @return List<Map>
	 */
	public List<Map<String, String>> reduceMaps(){
		List<Map<String, String>> res = new ArrayList<Map<String,String>>();
		try {
			if (resultSet == null) {
				return res;
			}
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			while (resultSet.next()) {
				Map<String, String> map = new HashMap<>(columnCount);
				for (int i = 1; i <= columnCount; i++) {
					map.put(resultSetMetaData.getColumnClassName(i), resultSet.getString(i));
				}
				res.add(map);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("reduceMaps出错");
		}
		return res;
	}
}
