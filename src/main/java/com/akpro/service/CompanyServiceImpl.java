package com.akpro.service;

import static com.akpro.util.Constants.IMAGE_PATH;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.akpro.bean.Company;
import com.akpro.bo.CompanyBo;
import com.akpro.repository.CompanyRepository;
import com.akpro.util.DateUtils;
import static com.akpro.util.Constants.*;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public List<CompanyBo> getAllCompanies() throws Exception {
		List<CompanyBo> companyBos = new ArrayList<>();
		
		List<Company> companies = companyRepository.findAll();
		
		for(Company company:companies) {
			CompanyBo companyBo = new CompanyBo();
			companyBo.setId(company.getId());
			companyBo.setCompanyName(company.getCompanyName());
			companyBo.setDescription(company.getDescription());
			companyBo.setWebsite(company.getWebsite());
			companyBo.setImageUrl(company.getImageUrl());
			
			File flie = new File(IMAGE_PATH + company.getImageUrl());
			byte[] imageByte = Files.readAllBytes(flie.toPath());
			companyBo.setImageData(Base64.encodeBase64String(imageByte));			
			
			companyBo.setCreatedDate(DateUtils.getUTCDate(company.getTimeCreated(), DATE_FORMAT));
			companyBo.setModifiedDate(DateUtils.getUTCDate(company.getTimeModified(), DATE_FORMAT));
			
			companyBos.add(companyBo);
		}
		
		return companyBos;
	}
	
	public CompanyBo getCompanyById(Integer companyId) throws Exception {		
		Optional<Company> optionalCompany = companyRepository.findById(companyId);
		Company company = optionalCompany.get();
		if(company!=null) {
			CompanyBo companyBo = new CompanyBo();
			companyBo.setId(company.getId());
			companyBo.setCompanyName(company.getCompanyName());
			companyBo.setDescription(company.getDescription());
			companyBo.setWebsite(company.getWebsite());
			companyBo.setImageUrl(company.getImageUrl());
			
			File flie = new File(IMAGE_PATH + company.getImageUrl());
			byte[] imageByte = Files.readAllBytes(flie.toPath());
			companyBo.setImageData(Base64.encodeBase64String(imageByte));	
			
			companyBo.setCreatedDate(DateUtils.getUTCDate(company.getTimeCreated(), DATE_FORMAT));
			companyBo.setModifiedDate(DateUtils.getUTCDate(company.getTimeModified(), DATE_FORMAT));
			
			return companyBo;
		}
		
		return null;
	}
	
	public void createOrUpdateCompany(CompanyBo companyBo) throws Exception {
		if(companyBo==null)
			throw new Exception("Send Object null");
		Company company = new Company();
		if(companyBo.getId()!=null && companyBo.getId()!=0)
			company = companyRepository.findById(companyBo.getId()).get();
		
		if(companyBo.getImageData()!=null && companyBo.getImageData()!=StringUtils.EMPTY) {
			byte[] imageByte=Base64.decodeBase64(companyBo.getImageData());

            FileOutputStream fos = new FileOutputStream(new File(IMAGE_PATH + company.getImageUrl()));
            fos.write(imageByte);
            fos.close();
		}
		
		company.setCompanyName(companyBo.getCompanyName());
		company.setDescription(companyBo.getDescription());
		company.setWebsite(companyBo.getWebsite());
		company.setImageUrl(companyBo.getImageUrl());
		
		companyRepository.save(company);
	}
	
	public void deleteCompanyById(Integer companyId) throws Exception {
		companyRepository.deleteById(companyId);
	}
}
