package edu.ijse.service.custom.impl;

import edu.ijse.dao.DaoFactory;
import edu.ijse.dao.custom.CustomerDao;
import edu.ijse.dto.CustomerDto;
import edu.ijse.entity.CustomerEntity;
import edu.ijse.service.custom.CustomerService;

import java.util.ArrayList;

public class CustomerServiceImpl implements CustomerService{

    private CustomerDao CustomerDao = (CustomerDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.CUSTOMER);

    @Override
    public String save(CustomerDto customerDto) throws Exception {
        CustomerEntity entity = getCustomerEntity(customerDto);
        return CustomerDao.create(entity)? "Success" : "Fail";
    }

    @Override
    public String update(CustomerDto customerDto) throws Exception {
        CustomerEntity entity = getCustomerEntity(customerDto);
        return CustomerDao.update(entity)? "Success" : "Fail";
    }

    @Override
    public String delete(String custIdString) throws Exception {
        return CustomerDao.delete(custIdString)?  "Success" : "Fail";
    }

    @Override
    public CustomerDto getCustomer(String custId) throws Exception {
        CustomerEntity entity = CustomerDao.get(custId);
        if(entity != null){
            return getCustomerDto(entity);
        }
        return null;
    }

    @Override
    public ArrayList<CustomerDto> getAll() throws Exception {
        ArrayList<CustomerEntity> customerEntitys = CustomerDao.getAll();

        if(customerEntitys !=null && !customerEntitys.isEmpty()){
            ArrayList<CustomerDto> customerDtos = new ArrayList<>();

            for(CustomerEntity customerEntity:customerEntitys){
                customerDtos.add(getCustomerDto(customerEntity));
            }
            return customerDtos;
        }
        return null;
    }

    private CustomerEntity getCustomerEntity(CustomerDto customerDto){
        return new CustomerEntity(
                customerDto.getId(),
                customerDto.getTitle(),
                customerDto.getName(),
                customerDto.getDob(),
                customerDto.getSalary(),
                customerDto.getAddress(),
                customerDto.getCity(),
                customerDto.getProvince(),
                customerDto.getPostal());
    }

    private CustomerDto getCustomerDto(CustomerEntity entity){
        return new CustomerDto(
                entity.getId(),
                entity.getTitle(),
                entity.getName(),
                entity.getDob(),
                entity.getSalary(),
                entity.getAddress(),
                entity.getCity(),
                entity.getProvince(),
                entity.getPostal());
    }
}
