package com.bcstudents.personnelmanagement.mapper;

import com.bcstudents.personnelmanagement.bean.Contract;

import java.util.List;

public interface ContractMapper {
    //    Retrieve contract by id
    Contract getContractById(Integer id);

    //   Retrieve all contracts and employees
    List<Contract> getContractAndEmp();

    //    Retrieve all contracts
    List<Contract> getAllContracts();

    //    Delete contract by id
    int deleteContractById(Integer id);

    //    Add contract
    int addContract(Contract contract);

    //    Update contract information
    int updateContract(Contract contract);

    List<Contract> query(String name);
}
