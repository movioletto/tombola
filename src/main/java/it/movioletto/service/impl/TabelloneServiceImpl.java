package it.movioletto.service.impl;

import it.movioletto.dao.NumeroUscitoDao;
import it.movioletto.dao.StanzaDao;
import it.movioletto.model.NumeroUscitoEntity;
import it.movioletto.model.NumeroUscitoEntityKey;
import it.movioletto.model.StanzaEntity;
import it.movioletto.model.TabellaEntity;
import it.movioletto.repository.NumeroUscitoRepository;
import it.movioletto.repository.StanzaRepository;
import it.movioletto.repository.TabellaRepository;
import it.movioletto.service.TabelloneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TabelloneServiceImpl implements TabelloneService {

	@Autowired
	private StanzaRepository stanzaRepository;

	@Autowired
	private NumeroUscitoRepository numeroUscitoRepository;

	@Autowired
	private TabellaRepository tabellaRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public StanzaDao creaStanza(String nome) {
		int leftLimit = 48;
		int rightLimit = 122;
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		StanzaEntity entity = new StanzaEntity();
		entity.setIdStanza(generatedString);
		entity.setNome(nome);

		StanzaEntity ritorno = stanzaRepository.save(entity);

		return modelMapper.map(ritorno, StanzaDao.class);
	}

	@Override
	public StanzaDao getStanza(String idStanza) {
		Optional<StanzaEntity> entityOptional = stanzaRepository.findById(idStanza);

		return entityOptional.map(stanzaEntity -> modelMapper.map(stanzaEntity, StanzaDao.class)).orElse(null);
	}

	@Override
	public List<NumeroUscitoDao> getNumeriUsciti(String idStanza) {
		List<NumeroUscitoDao> out = new ArrayList<>();

		Optional<List<NumeroUscitoEntity>> numeroUscitoEntityListOptional = numeroUscitoRepository.findByIdStanza(idStanza);

		numeroUscitoEntityListOptional.ifPresent(numeroUscitoEntities ->
				numeroUscitoEntities.forEach(numeroUscitoEntity -> out.add(new NumeroUscitoDao(numeroUscitoEntity.getId().getIdStanza(), numeroUscitoEntity.getId().getNumero(), numeroUscitoEntity.getData()))));

		return out;
	}

	@Override
	public void saveNumeroEstratto(String idStanza, int numeroEstratto) {
		NumeroUscitoEntityKey entityKey = new NumeroUscitoEntityKey();
		entityKey.setIdStanza(idStanza);
		entityKey.setNumero(numeroEstratto);

		NumeroUscitoEntity entity = new NumeroUscitoEntity();
		entity.setId(entityKey);
		entity.setData(new Date());

		numeroUscitoRepository.save(entity);
	}

	@Override
	public boolean existStanza(String idStanza) {
		Optional<StanzaEntity> entityOptional = stanzaRepository.findById(idStanza);

		return entityOptional.isPresent();
	}

	@Override
	public List<String> getGiocatoriPresenti(String idStanza) {
		List<String> out = new ArrayList<>();

		Optional<List<TabellaEntity>> entityListOptional = tabellaRepository.findAllByIdIdStanza(idStanza);

		entityListOptional.ifPresent(entityList -> {
			entityList.forEach(entity -> out.add(entity.getId().getIdTabella()));
		});

		return out;
	}
}
