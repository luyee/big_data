--- tableName: 3.1.1.1.1. 个人基本信息BaseInfo
 create table  BaseInfo( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 CARD_NO VARCHAR2(36) ,
 CARD_TYPE_CODE VARCHAR2(2) ,
 HEALTH_RECORD_NO VARCHAR2(17) ,
 EHR_CREATE_DATE DATE ,
 NAME VARCHAR2(128) ,
 SEX_CODE VARCHAR2(1) ,
 BIRTH_DATE DATE ,
 ID_TYPE_CODE VARCHAR2(2) ,
 ID_NO VARCHAR2(18) ,
 EMPLOYER_NAME VARCHAR2(70) ,
 TEL_NO VARCHAR2(128) ,
 CONTACT_NAME VARCHAR2(128) ,
 CONTACT_TEL_NO VARCHAR2(128) ,
 RESIDENCE_MARK VARCHAR2(1) ,
 NATIONALITY_CODE VARCHAR2(2) ,
 ABO_CODE VARCHAR2(1) ,
 RH_CODE VARCHAR2(1) ,
 EDUCATION_CODE VARCHAR2(5) ,
 OCCUPATION_CODE VARCHAR2(30) ,
 MARRIAGE_CODE VARCHAR2(2) ,
 DRUG_ALLERGY_MARK VARCHAR2(1) ,
 OP_HISTORY_MARK VARCHAR2(1) ,
 TRAUMA_HISTORY_MARK VARCHAR2(1) ,
 BLOOD_TRANSF_MARK VARCHAR2(1) ,
 DISABILITY_MARK VARCHAR2(1) ,
 GENETIC_DISEASE_HISTORY VARCHAR2(1000) ,
 EXHAUST_FACILITY_MARK VARCHAR2(1) ,
 EXHAUST_FACILITY_TYPE_CODE VARCHAR2(1) ,
 FUEL_TYPE_CODE VARCHAR2(1) ,
 WATER_TYPE_CODE VARCHAR2(1) ,
 TOILET_TYPE_CODE VARCHAR2(1) ,
 LIVESTOCK_PEN_TYPE_CODE VARCHAR2(1) ,
 OPERATION_HISTORY VARCHAR2(1000) ,
 SEC_TYPE_CODE VARCHAR2(2) ,
 CREATE_OPERATOR VARCHAR2(200) ,
 EMPLOYER_TEL_NO VARCHAR2(128) ,
 CREATE_TIME DATE ,
 CARD_END_DATE Date ,
 CONTACT_RELATIONSHIP VARCHAR2(128) ,
 COOPERATIVE_NO VARCHAR2(128) ,
 ASTHMA_MARK VARCHAR2(1) ,
 HEDRT_DIS_MARK VARCHAR2(1) ,
 CARDIOVASCULAR_CODE VARCHAR2(1) ,
 EPILEPSY_MARK VARCHAR2(1) ,
 COAGULOPATHY_MARK VARCHAR2(1) ,
 DIABETES_MARK VARCHAR2(1) ,
 GLAUCOMA_MARK VARCHAR2(1) ,
 DIALYSIS_MARK VARCHAR2(1) ,
 ORGAN_TRANS_MARK VARCHAR2(1) ,
 ORGAN_DEFECT_MARK VARCHAR2(1) ,
 REMOVA_PRO_MARK VARCHAR2(1) ,
 CARDIAC_PAC_MARK VARCHAR2(1) ,
 ORTHER_MEDICAL_ALERT VARCHAR2(128) ,
 PSYCHIATRIC_MARK VARCHAR2(1) ); 
comment on table BaseInfo.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo.ORG_CODE is '医疗机构代码';
comment on table BaseInfo.PATIENT_ID is '病人ID';
comment on table BaseInfo.CARD_NO is '居民健康卡号';
comment on table BaseInfo.CARD_TYPE_CODE is '卡类型代码';
comment on table BaseInfo.HEALTH_RECORD_NO is '城乡居民健康档案编号';
comment on table BaseInfo.EHR_CREATE_DATE is '建档日期';
comment on table BaseInfo.NAME is '本人姓名';
comment on table BaseInfo.SEX_CODE is '性别代码';
comment on table BaseInfo.BIRTH_DATE is '出生日期';
comment on table BaseInfo.ID_TYPE_CODE is '身份证件类别代码';
comment on table BaseInfo.ID_NO is '身份证件号码';
comment on table BaseInfo.EMPLOYER_NAME is '工作单位名称';
comment on table BaseInfo.TEL_NO is '本人电话号码';
comment on table BaseInfo.CONTACT_NAME is '联系人-姓名';
comment on table BaseInfo.CONTACT_TEL_NO is '联系人-电话';
comment on table BaseInfo.RESIDENCE_MARK is '常住地址户籍标志';
comment on table BaseInfo.NATIONALITY_CODE is '民族代码';
comment on table BaseInfo.ABO_CODE is 'ABO血型代码';
comment on table BaseInfo.RH_CODE is 'Rh血型代码';
comment on table BaseInfo.EDUCATION_CODE is '学历代码';
comment on table BaseInfo.OCCUPATION_CODE is '职业类别代码';
comment on table BaseInfo.MARRIAGE_CODE is '婚姻状况代码';
comment on table BaseInfo.DRUG_ALLERGY_MARK is '药物过敏史标志';
comment on table BaseInfo.OP_HISTORY_MARK is '手术史标志';
comment on table BaseInfo.TRAUMA_HISTORY_MARK is '外伤史标志';
comment on table BaseInfo.BLOOD_TRANSF_MARK is '输血史标志';
comment on table BaseInfo.DISABILITY_MARK is '残疾标志';
comment on table BaseInfo.GENETIC_DISEASE_HISTORY is '遗传性疾病史';
comment on table BaseInfo.EXHAUST_FACILITY_MARK is '家庭厨房排风设施标志';
comment on table BaseInfo.EXHAUST_FACILITY_TYPE_CODE is '家庭厨房排风设施类别代码';
comment on table BaseInfo.FUEL_TYPE_CODE is '家庭燃料类型类别代码';
comment on table BaseInfo.WATER_TYPE_CODE is '家庭饮水类别代码';
comment on table BaseInfo.TOILET_TYPE_CODE is '家庭厕所类别代码';
comment on table BaseInfo.LIVESTOCK_PEN_TYPE_CODE is '家庭禽畜栏类别代码';
comment on table BaseInfo.OPERATION_HISTORY is '手术史';
comment on table BaseInfo.SEC_TYPE_CODE is '医疗保险类别代码';
comment on table BaseInfo.CREATE_OPERATOR is '建档者姓名';
comment on table BaseInfo.EMPLOYER_TEL_NO is '工作单位电话号码';
comment on table BaseInfo.CREATE_TIME is '建卡（注册）时间';
comment on table BaseInfo.CARD_END_DATE is '卡有效期';
comment on table BaseInfo.CONTACT_RELATIONSHIP is '联系人：关系';
comment on table BaseInfo.COOPERATIVE_NO is '新农合证（卡）号';
comment on table BaseInfo.ASTHMA_MARK is '哮喘标志';
comment on table BaseInfo.HEDRT_DIS_MARK is '心脏病标志';
comment on table BaseInfo.CARDIOVASCULAR_CODE is '心脑血管病标志';
comment on table BaseInfo.EPILEPSY_MARK is '癫痫病标志';
comment on table BaseInfo.COAGULOPATHY_MARK is '凝血紊乱标志';
comment on table BaseInfo.DIABETES_MARK is '糖尿病标志';
comment on table BaseInfo.GLAUCOMA_MARK is '青光眼标志';
comment on table BaseInfo.DIALYSIS_MARK is '透析标志';
comment on table BaseInfo.ORGAN_TRANS_MARK is '器官移植标志';
comment on table BaseInfo.ORGAN_DEFECT_MARK is '器官缺失标志';
comment on table BaseInfo.REMOVA_PRO_MARK is '可装卸的义肢标志';
comment on table BaseInfo.CARDIAC_PAC_MARK is '心脏起搏器标志';
comment on table BaseInfo.ORTHER_MEDICAL_ALERT is '其他医学警示名称';
comment on table BaseInfo.PSYCHIATRIC_MARK is '精神病标志';
commit; 


--- tableName: 3.1.1.1.2. 个人基本信息-地址信息BASEINFO_ADDRESS
 create table  BASEINFO_ADDRESS( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 ADDRESS_TYPE_CODE VARCHAR2(128) ,
 ADDR_PROVINCE VARCHAR2(200) ,
 ADDR_CITY VARCHAR2(200) ,
 ADDR_COUNTY VARCHAR2(200) ,
 ADDR_TOWN VARCHAR2(200) ,
 ADDR_VILLAGE VARCHAR2(200) ,
 ADDR_HOUSE_NO VARCHAR2(200) ,
 POSTAL_CODE VARCHAR2(6) ); 
comment on table BASEINFO_ADDRESS.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BASEINFO_ADDRESS.ORG_CODE is '医疗机构代码';
comment on table BASEINFO_ADDRESS.PATIENT_ID is '病人ID';
comment on table BASEINFO_ADDRESS.ADDRESS_TYPE_CODE is '地址类别代码';
comment on table BASEINFO_ADDRESS.ADDR_PROVINCE is '地址-省（自治区、直辖市）';
comment on table BASEINFO_ADDRESS.ADDR_CITY is '地址-市（地区、州）';
comment on table BASEINFO_ADDRESS.ADDR_COUNTY is '地址-县（区）';
comment on table BASEINFO_ADDRESS.ADDR_TOWN is '地址-乡（镇、街道办事处）';
comment on table BASEINFO_ADDRESS.ADDR_VILLAGE is '地址-村（街、路、弄等）';
comment on table BASEINFO_ADDRESS.ADDR_HOUSE_NO is '地址-门牌号码';
comment on table BASEINFO_ADDRESS.POSTAL_CODE is '邮政编码';
commit; 


--- tableName: 3.1.1.1.3. 残疾情况BaseInfo_Disability
 create table  BaseInfo_Disability( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 DISABILITY_CODE VARCHAR2(2) ); 
comment on table BaseInfo_Disability.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_Disability.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_Disability.PATIENT_ID is '病人ID';
comment on table BaseInfo_Disability.DISABILITY_CODE is '残疾情况代码';
commit; 


--- tableName: 3.1.1.1.4. 过敏源BaseInfo_Allergens
 create table  BaseInfo_Allergens( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 DRUG_ALLERGENS_CODE VARCHAR2(3) ); 
comment on table BaseInfo_Allergens.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_Allergens.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_Allergens.PATIENT_ID is '病人ID';
comment on table BaseInfo_Allergens.DRUG_ALLERGENS_CODE is '药物过敏源代码';
commit; 


--- tableName: 3.1.1.1.5. 环境危险因素BaseInfo_Envrisk
 create table  BaseInfo_Envrisk( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 ENV_RISK_TYPE_CODE VARCHAR2(1) ); 
comment on table BaseInfo_Envrisk.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_Envrisk.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_Envrisk.PATIENT_ID is '病人ID';
comment on table BaseInfo_Envrisk.ENV_RISK_TYPE_CODE is '环境危险因素暴露类别代码';
commit; 


--- tableName: 3.1.1.1.6. 疾病史BaseInfo_DiseaseHistory
 create table  BaseInfo_DiseaseHistory( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 PAST_SICKNESS_TYPE_CODE VARCHAR2(2) ,
 ID VARCHAR2(128) ,
 PAST_SICKNESS_CONFIRM_DATE DATE ); 
comment on table BaseInfo_DiseaseHistory.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_DiseaseHistory.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_DiseaseHistory.PATIENT_ID is '病人ID';
comment on table BaseInfo_DiseaseHistory.PAST_SICKNESS_TYPE_CODE is '既往患病种类代码';
comment on table BaseInfo_DiseaseHistory.ID is '序列号';
comment on table BaseInfo_DiseaseHistory.PAST_SICKNESS_CONFIRM_DATE is '既往患病确诊日期';
commit; 


--- tableName: 3.1.1.1.7. 家族史BaseInfo_FamHistory
 create table  BaseInfo_FamHistory( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 ID VARCHAR2(128) ,
 FAMILY_DISEASE_CODE VARCHAR2(2) ,
 PATIENT_RELATION_CODE VARCHAR2(2) ); 
comment on table BaseInfo_FamHistory.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_FamHistory.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_FamHistory.PATIENT_ID is '病人ID';
comment on table BaseInfo_FamHistory.ID is '序列号';
comment on table BaseInfo_FamHistory.FAMILY_DISEASE_CODE is '家族性疾病名称代码';
comment on table BaseInfo_FamHistory.PATIENT_RELATION_CODE is '患者与本人关系代码';
commit; 


--- tableName: 3.1.1.1.8. 手术记录BaseInfo_OpHistory
 create table  BaseInfo_OpHistory( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 ID VARCHAR2(128) ,
 OPERATION_NAME VARCHAR2(128) ,
 OPERATION_CODE VARCHAR2(128) ,
 OPERATION_DTIME DATE ,
 OPER_FORM_NO VARCHAR2(128) ); 
comment on table BaseInfo_OpHistory.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_OpHistory.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_OpHistory.PATIENT_ID is '病人ID';
comment on table BaseInfo_OpHistory.ID is '序列号';
comment on table BaseInfo_OpHistory.OPERATION_NAME is '手术/操作名称';
comment on table BaseInfo_OpHistory.OPERATION_CODE is '手术（操作）代码';
comment on table BaseInfo_OpHistory.OPERATION_DTIME is '手术（操作）日期时间';
comment on table BaseInfo_OpHistory.OPER_FORM_NO is '手术记录表单号';
commit; 


--- tableName: 3.1.1.1.9. 输血史BaseInfo_Bloodtrans
 create table  BaseInfo_Bloodtrans( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 ID VARCHAR2(128) ,
 BLOOD_TRANSF_CAUSE VARCHAR2(100) ,
 BLOOD_TYPE VARCHAR2(1) ,
 BLOOD NUMBER(12,2) ,
 BLOOD_TRANSF_DTIME DATE ); 
comment on table BaseInfo_Bloodtrans.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_Bloodtrans.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_Bloodtrans.PATIENT_ID is '病人ID';
comment on table BaseInfo_Bloodtrans.ID is '序列号';
comment on table BaseInfo_Bloodtrans.BLOOD_TRANSF_CAUSE is '输血原因';
comment on table BaseInfo_Bloodtrans.BLOOD_TYPE is '输血类型代码';
comment on table BaseInfo_Bloodtrans.BLOOD is '输血量(单位)';
comment on table BaseInfo_Bloodtrans.BLOOD_TRANSF_DTIME is '输血日期时间';
commit; 


--- tableName: 3.1.1.1.10. 外伤史BaseInfo_TraumaHistory
 create table  BaseInfo_TraumaHistory( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 ID VARCHAR2(128) ,
 TRAUMA_NAME VARCHAR2(100) ,
 TRAUMA_OCCUR_DTIME DATE ); 
comment on table BaseInfo_TraumaHistory.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_TraumaHistory.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_TraumaHistory.PATIENT_ID is '病人ID';
comment on table BaseInfo_TraumaHistory.ID is '序列号';
comment on table BaseInfo_TraumaHistory.TRAUMA_NAME is '外伤名称';
comment on table BaseInfo_TraumaHistory.TRAUMA_OCCUR_DTIME is '外伤发生日期时间';
commit; 


--- tableName: 3.1.1.1.11. 医疗费用支付方式BaseInfo_Payway
 create table  BaseInfo_Payway( 
 LAST_UPDATE_DTIME DATE ,
 ORG_CODE VARCHAR2(50) ,
 PATIENT_ID VARCHAR2(128) ,
 PAY_WAY_CODE VARCHAR2(2) ); 
comment on table BaseInfo_Payway.LAST_UPDATE_DTIME is '最后修改时间';
comment on table BaseInfo_Payway.ORG_CODE is '医疗机构代码';
comment on table BaseInfo_Payway.PATIENT_ID is '病人ID';
comment on table BaseInfo_Payway.PAY_WAY_CODE is '医疗费用支付方式代码';
commit; 


