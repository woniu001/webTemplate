package net.zicp.xiaochangwei.web.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Comparable<User>, Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private String username;

	private String password;

	private String telphone;

	private Long roleId;

	/*
	 * 1:正常， 0禁用
	 */
	private int state;

	private Date startTime;

	private String token;

	private UserInfo userInfo;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public static void main(String[] args) {

		/**
		 * 列表对象转JSON以及JSON转为对象列表 用于redis缓存
		 */
		// List<User> users = new LinkedList<User>();
		// for(Long i=0L ; i<3;i++){
		// User u = new User();
		// u.setUsername("name"+i);
		// for(Long j = 200L;j<204;j++){
		// Role r = new Role();
		// r.setRoleName("role"+i+j);
		// for(Long k = 400L;k<402;k++){
		// Permission p = new Permission();
		// p.setPermission("permission"+i+j+k);
		// r.getPermissions().add(p);
		// }
		// u.getRoles().add(r);
		// }
		// users.add(u);
		// }
		// String jsonString = JSON.toJSONString(users);
		// System.out.println(jsonString);
		//
		// List<User> uu = JSON.parseArray(jsonString,User.class);
		// for(User uuu : uu){
		// System.out.println(uuu.getRoles().get(0).getPermissions().get(0).getPermission());
		// }
	}

	@Override
	public int compareTo(User o) {
		return 0;
	}
}
