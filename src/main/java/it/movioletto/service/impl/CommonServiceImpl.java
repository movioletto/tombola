package it.movioletto.service.impl;

import it.movioletto.dto.AnimaleDto;
import it.movioletto.repository.AnimaleRepository;
import it.movioletto.service.CommonService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {

  private final AnimaleRepository animaleRepository;
  private final ModelMapper modelMapper;

  public CommonServiceImpl(AnimaleRepository animaleRepository, ModelMapper modelMapper) {
    this.animaleRepository = animaleRepository;
    this.modelMapper = modelMapper;
  }


  @Override
  public List<AnimaleDto> findAllAnimale() {
    return animaleRepository.findAll().stream()
        .map(animaleEntity -> modelMapper.map(animaleEntity, AnimaleDto.class))
        .collect(Collectors.toList());
  }

}
