package com.akpro.service;

import static com.akpro.util.Constants.DATE_FORMAT;
import static com.akpro.util.Constants.IMAGE_PATH;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.akpro.bean.Company;
import com.akpro.bo.CompanyBo;
import com.akpro.bo.ListRS;
import com.akpro.repository.CompanyRepository;
import com.akpro.util.DateUtils;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CompanyServiceImpl.class);
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public ListRS<CompanyBo> getAllCompanies(Integer page, Integer size, String sortingDirection, String sortBy, String search) throws Exception {
		List<CompanyBo> companyBos = new ArrayList<>();
		search = "%"+new String(Hex.decodeHex(search), "UTF-8")+"%";
		LOGGER.info("Search: "+search);
		
		Direction direction;
		if (sortingDirection.equals("ASC")) {
			direction = Sort.Direction.ASC;
		} else {
			direction = Sort.Direction.DESC;
		}
		
		Page<Company> companies = companyRepository.findBySearch(search, PageRequest.of(page-1, size, direction, sortBy));
		
		for(Company company:companies) {
			CompanyBo companyBo = new CompanyBo();
			companyBo.setId(company.getId());
			companyBo.setCompanyName(company.getCompanyName());
			companyBo.setDescription(company.getDescription());
			companyBo.setWebsite(company.getWebsite());
			companyBo.setImageUrl(company.getImageUrl());
			companyBo.setImageData(company.getImageUrl());			
			
			companyBo.setCreatedDate(DateUtils.getUTCDate(company.getTimeCreated(), DATE_FORMAT));
			companyBo.setModifiedDate(DateUtils.getUTCDate(company.getTimeModified(), DATE_FORMAT));
			
			companyBos.add(companyBo);
		}
		
		ListRS<CompanyBo> listRs = new ListRS<>();
		listRs.setData(companyBos);
		listRs.setCount(companies.getTotalElements());
		listRs.setPageCount(companies.getTotalPages());
		
		return listRs;
	}
	
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
			companyBo.setImageData(company.getImageUrl());			
			
			companyBo.setCreatedDate(DateUtils.getUTCDate(company.getTimeCreated(), DATE_FORMAT));
			companyBo.setModifiedDate(DateUtils.getUTCDate(company.getTimeModified(), DATE_FORMAT));
			
			companyBos.add(companyBo);
		}
		
		return companyBos;
	}
	
	public CompanyBo getCompanyById(Integer companyId) throws Exception {	
		if(companyId==null || companyId==0)
			throw new Exception("Id is null or 0");
		CompanyBo companyBo = null;
		Company company = companyRepository.findById(companyId).get();
		if(company!=null) {
			companyBo = new CompanyBo();
			companyBo.setId(company.getId());
			companyBo.setCompanyName(company.getCompanyName());
			companyBo.setDescription(company.getDescription());
			companyBo.setWebsite(company.getWebsite());
			companyBo.setImageUrl(company.getImageUrl());
			
			File file = new File(IMAGE_PATH + company.getImageUrl());
			if(file.exists()) {
				byte[] imageByte = Files.readAllBytes(file.toPath());
				companyBo.setImageData(Base64.encodeBase64String(imageByte));
			}
			
			companyBo.setCreatedDate(DateUtils.getUTCDate(company.getTimeCreated(), DATE_FORMAT));
			companyBo.setModifiedDate(DateUtils.getUTCDate(company.getTimeModified(), DATE_FORMAT));
		}
		
		return companyBo;
	}
	
	public void createOrUpdateCompany(CompanyBo companyBo) throws Exception {
		if(companyBo==null)
			throw new Exception("Send Object null");
		Company company = new Company();
		if(companyBo.getId()!=null && companyBo.getId()!=0)
			company = companyRepository.findById(companyBo.getId()).get();
		
		if(companyBo.getImageData()!=null && companyBo.getImageData()!=StringUtils.EMPTY) {
			
			if(company.getImageUrl()!=null && !StringUtils.equalsIgnoreCase(company.getImageUrl(), companyBo.getImageUrl())) {
				File file = new File(IMAGE_PATH + company.getImageUrl());
				if(file.exists())
					file.delete();
			}
			
			byte[] imageByte=Base64.decodeBase64(companyBo.getImageData());
            FileOutputStream fos = new FileOutputStream(new File(IMAGE_PATH + companyBo.getImageUrl()));
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
		Optional<Company> optionalCompany = companyRepository.findById(companyId);
		Company company = optionalCompany.get();
		
		if(company.getImageUrl()!=null)
			new File(IMAGE_PATH + company.getImageUrl()).delete();
		
		companyRepository.delete(company);
	}
}
