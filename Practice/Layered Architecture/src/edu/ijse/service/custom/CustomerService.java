package edu.ijse.service.custom;

import edu.ijse.dto.CustomerDto;
import edu.ijse.service.SuperService;

import java.util.ArrayList;

public interface CustomerService extends SuperService {
    String save(CustomerDto customerDto)throws Exception;
    String update(CustomerDto customerDto)throws Exception;
    String delete(String custId)throws Exception;
    CustomerDto getCustomer(String custId)throws Exception;
    ArrayList<CustomerDto> getAll()throws Exception;
}
