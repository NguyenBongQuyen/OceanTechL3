package com.octl3.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoredProcedure {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Parameter {
        public static final String REGISTRATION_JSON = "registration_data_json";
        public static final String REGISTRATION_ID_PARAM = "registration_id_param";
        public static final String SALARY_INCREMENT_JSON = "salary_increment_data_json";
        public static final String SALARY_INCREMENT_ID_PARAM = "salary_increment_id_param";
        public static final String PROMOTION_JSON = "promotion_data_json";
        public static final String PROMOTION_ID_PARAM = "promotion_id_param";
        public static final String STATUS_PARAM = "status_param";
        public static final String EMPLOYEE_JSON = "employee_data_json";
        public static final String EMPLOYEE_ID_PARAM = "employee_id_param";
        public static final String CERTIFICATE_JSON = "certificate";
        public static final String CERTIFICATE_ID_PARAM = "certificate_id_param";
        public static final String RELATIONSHIP_JSON = "relationship_data_json";
        public static final String RELATIONSHIP_ID_PARAM = "relationship_id_param";
        public static final String END_OF_EMPLOYEE_JSON = "end_of_employee_data_json";
        public static final String END_OF_EMPLOYEE_ID_PARAM = "end_of_employee_id_param";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
        public static final String REGISTRATION_DTO_MAPPER = "RegistrationDtoMapper";
        public static final String SALARY_INCREMENT_DTO_MAPPER = "SalaryIncrementDtoMapper";

        public static final String EMPLOYEE_DTO_MAPPER = "EmployeeDtoMapper";
        public static final String CERTIFICATE_DTO_MAPPER = "CertificateDtoMapper";
        public static final String RELATIONSHIP_FAMILY_DTO_MAPPER = "RelationshipFamilyDtoMapper";
        public static final String PROMOTION_DTO_MAPPER = "PromotionDtoMapper";
        public static final String END_OF_EMPLOYEE_DTO_MAPPER = "EndOfEmployeeDtoMapper";
    }

    public static class Employee {
        public static final String CREATE = "create_employee";
        public static final String GET_BY_ID = "get_employee_by_id";
        public static final String GET_ALL = "get_all_employees";
        public static final String UPDATE = "update_employee";
        public static final String DELETE = "delete_employee";
    }

    public static class Certificate {
        public static final String CREATE = "create_certificate";
        public static final String GET_BY_ID = "get_certificate_by_id";
        public static final String GET_ALL = "get_all_certificates";
        public static final String UPDATE = "update_certificate";
        public static final String DELETE = "delete_certificate";
    }

    public static class Relationship {
        public static final String CREATE = "create_relationship";
        public static final String GET_BY_ID = "get_relationship_by_id";
        public static final String GET_ALL = "get_all_relationships";
        public static final String UPDATE = "update_relationship";
        public static final String DELETE = "delete_relationship";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Registration {
        public static final String CREATE_REGISTRATION = "create_registration";
        public static final String GET_ALL_REGISTRATION = "get_all_registration";
        public static final String GET_REGISTRATION_BY_ID = "get_registration_by_id";
        public static final String GET_REGISTRATION_BY_STATUS = "get_registration_by_status";
        public static final String SUBMIT_REGISTRATION = "submit_registration";
        public static final String UPDATE_REGISTRATION_BY_LEADER = "update_registration_by_leader";
        public static final String UPDATE_REGISTRATION_BY_MANAGER = "update_registration_by_manager";
        public static final String DELETE_REGISTRATION = "delete_registration";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Promotion {
        public static final String CREATE_PROMOTION = "create_promotion";
        public static final String GET_ALL_PROMOTION = "get_all_promotion";
        public static final String GET_PROMOTION_BY_ID = "get_promotion_by_id";
        public static final String GET_PROMOTION_BY_STATUS = "get_promotion_by_status";
        public static final String SUBMIT_PROMOTION = "submit_promotion";
        public static final String UPDATE_PROMOTION_BY_LEADER = "update_promotion_by_leader";
        public static final String UPDATE_PROMOTION_BY_MANAGER = "update_promotion_by_manager";
        public static final String DELETE_PROMOTION = "delete_promotion";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class SalaryIncrement {
        public static final String CREATE_SALARY_INCREMENT = "create_salary_increment";
        public static final String GET_ALL_SALARY_INCREMENT = "get_all_salary_increment";
        public static final String GET_SALARY_INCREMENT_BY_ID = "get_salary_increment_by_id";
        public static final String GET_SALARY_INCREMENT_BY_STATUS = "get_salary_increment_by_status";
        public static final String SUBMIT_SALARY_INCREMENT = "submit_salary_increment";
        public static final String UPDATE_SALARY_INCREMENT_BY_LEADER = "update_salary_increment_by_leader";
        public static final String UPDATE_SALARY_INCREMENT_BY_MANAGER = "update_salary_increment_by_manager";
        public static final String DELETE_SALARY_INCREMENT = "delete_salary_increment";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ProposalAdvice {

    }

    public static class EndOfEmployee {
        public static final String CREATE = "create_end_of_employee";
        public static final String GET_BY_ID = "get_end_of_employee_by_id";
        public static final String GET_ALL = "get_all_end_of_employees";
        public static final String UPDATE = "update_end_of_employee";
        public static final String DELETE = "delete_end_of_employee";
    }

}
