package com.octl3.api.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StoredProcedure {
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Parameter {
        public static final String USER_JSON = "user_data_json";
        public static final String USERNAME_PARAM = "username_param";
        public static final String ROLE_NAME_PARAM = "role_name_param";
        public static final String ROLE_ID_PARAM = "role_id_param";
        public static final String REGISTRATION_JSON = "registration_data_json";
        public static final String REGISTRATION_ID_PARAM = "registration_id_param";
        public static final String SALARY_INCREMENT_JSON = "salary_increment_data_json";
        public static final String SALARY_INCREMENT_ID_PARAM = "salary_increment_id_param";
        public static final String PROMOTION_JSON = "promotion_data_json";
        public static final String PROMOTION_ID_PARAM = "promotion_id_param";
        public static final String PROPOSAL_ADVICE_JSON = "proposal_advice_data_json";
        public static final String PROPOSAL_ADVICE_ID_PARAM = "proposal_advice_id_param";
        public static final String STATUS_PARAM = "status_param";
        public static final String EMPLOYEE_JSON = "employee_data_json";
        public static final String EMPLOYEE_ID_PARAM = "employee_id_param";
        public static final String CERTIFICATE_JSON = "certificate_data_json";
        public static final String CERTIFICATE_ID_PARAM = "certificate_id_param";
        public static final String RELATIONSHIP_JSON = "relationship_data_json";
        public static final String RELATIONSHIP_ID_PARAM = "relationship_id_param";
        public static final String PROFILE_END_JSON = "profile_end_data_json";
        public static final String PROFILE_END_ID_PARAM = "profile_end_id_param";

    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Mapper {
        public static final String USER_RESPONSE_DTO_MAPPER = "UserResponseDtoMapper";
        public static final String ROLE_DTO_MAPPER = "RoleDtoMapper";
        public static final String USER_DTO_MAPPER = "UserDtoMapper";
        public static final String REGISTRATION_DTO_MAPPER = "RegistrationDtoMapper";
        public static final String SALARY_INCREMENT_DTO_MAPPER = "SalaryIncrementDtoMapper";
        public static final String EMPLOYEE_DTO_MAPPER = "EmployeeDtoMapper";
        public static final String CERTIFICATE_DTO_MAPPER = "CertificateDtoMapper";
        public static final String RELATIONSHIP_DTO_MAPPER = "RelationshipDtoMapper";
        public static final String PROPOSAL_ADVICE_DTO_MAPPER = "ProposalAdviceDtoMapper";
        public static final String PROMOTION_DTO_MAPPER = "PromotionDtoMapper";
        public static final String PROFILE_END_DTO_MAPPER = "ProfileEndDtoMapper";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Employee {
        public static final String CREATE = "create_employee";
        public static final String GET_BY_ID = "get_employee_by_id";
        public static final String GET_ALL = "get_all_employees";
        public static final String UPDATE = "update_employee";
        public static final String DELETE = "delete_employee";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Certificate {
        public static final String CREATE = "create_certificate";
        public static final String GET_BY_ID = "get_certificate_by_id";
        public static final String GET_BY_EMPLOYEE_ID = "get_certificate_by_employee_id";
        public static final String UPDATE = "update_certificate";
        public static final String DELETE = "delete_certificate";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Relationship {
        public static final String CREATE = "create_relationship";
        public static final String GET_BY_ID = "get_relationship_by_id";
        public static final String GET_BY_EMPLOYEE_ID = "get_relationship_by_employee_id";
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
        public static final String CREATE_PROPOSAL_ADVICE = "create_proposal_advice";
        public static final String GET_ALL_PROPOSAL_ADVICE = "get_all_proposal_advice";
        public static final String GET_PROPOSAL_ADVICE_BY_ID = "get_proposal_advice_by_id";
        public static final String GET_PROPOSAL_ADVICE_BY_STATUS = "get_proposal_advice_by_status";
        public static final String SUBMIT_PROPOSAL_ADVICE = "submit_proposal_advice";
        public static final String UPDATE_PROPOSAL_ADVICE_BY_LEADER = "update_proposal_advice_by_leader";
        public static final String UPDATE_PROPOSAL_ADVICE_BY_MANAGER = "update_proposal_advice_by_manager";
        public static final String DELETE_PROPOSAL_ADVICE = "delete_proposal_advice";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ProfileEnd {
        public static final String CREATE = "create_profile_end";
        public static final String GET_BY_ID = "get_profile_end_by_id";
        public static final String GET_ALL = "get_all_profile_end";
        public static final String GET_BY_STATUS = "get_profile_end_by_status";
        public static final String UPDATE_BY_MANAGER = "update_profile_end_by_manager";
        public static final String UPDATE_BY_LEADER = "update_profile_end_by_leader";
        public static final String SUBMIT = "submit_profile_end";
        public static final String DELETE = "delete_profile_end";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class User {
        public static final String CREATE_USER = "create_user";
        public static final String GET_USER_BY_USERNAME = "get_user_by_username";
        public static final String GET_ROLE_BY_NAME = "get_role_by_name";
        public static final String GET_ROLE_BY_ID = "get_role_by_id";
        public static final String IS_EXIST_ROLE_BY_ID = "is_exist_role_by_id";



    }

}
