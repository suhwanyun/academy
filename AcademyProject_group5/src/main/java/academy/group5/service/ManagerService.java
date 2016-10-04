package academy.group5.service;

import academy.group5.dto.Manager;

public interface ManagerService {
	/**
	 * 로그인
	 * @param managerId
	 * @param managerPass
	 * @return
	 */
	Manager managerLogin(String managerId, String managerPass);
}
