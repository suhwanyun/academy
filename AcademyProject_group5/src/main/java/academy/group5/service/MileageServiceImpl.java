package academy.group5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import academy.group5.dto.MileageProduct;
import academy.group5.dto.UserData;
import academy.group5.dto.UserMileage;
import academy.group5.dto.etc.UserMileageProduct;
import academy.group5.exception.WrongRequestException;
import academy.group5.repo.LoginRepo;
import academy.group5.repo.MileageRepo;

@Service
@Transactional
public class MileageServiceImpl implements MileageService {

	@Autowired
	MileageRepo mileRepo;
	
	@Autowired
	LoginRepo loginRepo;
	
	@Override
	public List<MileageProduct> productList() {
		return mileRepo.getAllProduct();
	}

	@Override
	public MileageProduct productOne(Integer productId) {
		MileageProduct productData = mileRepo.getProduct(productId);
		if(productData == null){
			throw new WrongRequestException();
		}
		return productData;
	}
	
	@Override
	public UserMileageProduct productOneByUser(String userId, Integer productId) {
		UserMileageProduct productData = mileRepo.getProductByUser(new UserMileage(userId,productId));
		if(productData == null){
			throw new WrongRequestException();
		}
		return productData;
	}

	@Override
	public List<MileageProduct> shoppingList(String userId) {
		return mileRepo.getAllProductByUser(userId);
	}

	@Override
	public UserData buyProduct(String userId, Integer productId) {
		
		MileageProduct productData = mileRepo.getProduct(productId);
		Integer productCost = productData.getProductCost();
		Integer userMileage = mileRepo.getMileage(userId);
		
		if(userMileage == null || productData == null){
			throw new WrongRequestException();
		} else if(userMileage < productCost) {
			throw new WrongRequestException("마일리지가 부족합니다.");
		}
		UserMileage buyData = new UserMileage(userId, productId);
		// 기존에 구입한 기록이 있는지 확인
		Integer remainCount = mileRepo.getProductRemain(buyData);
		if(remainCount == null){
			// 신규 구입
			int result = mileRepo.setProduct(buyData);
			if(result != 1){
				throw new WrongRequestException();
			}
		} else {
			// 이미 구입한 물품은 수량을 증가
			buyData.setProductRemain(remainCount + 1);
			int result = mileRepo.updateProduct(buyData);
			if(result != 1){
				throw new WrongRequestException();
			}
		}
		// 마일리지 감소
		int result = mileRepo.setMileage(new UserData(userId, userMileage-productCost));
		if(result != 1){
			throw new WrongRequestException();
		}
		UserData newUserData = loginRepo.getUser(userId);
		if(newUserData == null){
			throw new WrongRequestException();
		}
		return newUserData;
	}

	@Override
	public boolean useProduct(String userId, Integer productId) {
		UserMileage useData = new UserMileage(userId, productId);
		// 남은 수량 확인
		Integer remainCount = mileRepo.getProductRemain(useData);
		if(remainCount == null || remainCount == 0){
			throw new WrongRequestException();
		}
		// 마지막 물품이었다면 DB에서 제거
		if(--remainCount == 0){
			mileRepo.delProduct(useData);
		} 
		// 아니라면 수량 -1
		else {
			useData.setProductRemain(remainCount);
			int result = mileRepo.updateProduct(useData);
			if(result != 1){
				throw new WrongRequestException();
			}
		}
		
		return false;
	}

}
