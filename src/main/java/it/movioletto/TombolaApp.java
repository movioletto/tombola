package it.movioletto;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class TombolaApp {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(TombolaApp.class, args);
	}

//	@PostConstruct
//	private void initDb() {
//		String[] sqlStatements = {
//				"drop table animale if exists",
//				"create table animale(id_animale serial, nome varchar(20))",
//				"insert into animale(id_animale, nome) values (1, 'aguglia'), (2, 'anguilla'), (3, 'alborella'), " +
//						"(4, 'aragosta'), (5, 'anemone'), (6, 'astice'), (7, 'anatra'), (8, 'astore'), (9, 'aquila'), " +
//						"(10, 'antilope'), (11, 'avvoltoio'), (12, 'airone'), (13, 'alpaca'), (14, 'ape'), " +
//						"(15, 'alercchino'), (16, 'bisonte'), (17, 'balena'), (18, 'babbuino'), (19, 'bufalo'), " +
//						"(20, 'basilisco'), (21, 'bassarisco'), (22, 'bagarozzo'), (23, 'balenottora'), (24, 'baco'), " +
//						"(25, 'barbagianni'), (26, 'barracuda'), (27, 'biscia'), (28, 'bue'), (29, 'cernia'), " +
//						"(30, 'cavalluccio marino'), (31, 'carpa'), (32, 'calamaro'), (33, 'canocchia'), " +
//						"(34, 'cavallo'), (35, 'caracal'), (36, 'cammello'), (37, 'cervo'), (38, 'cuculo'), " +
//						"(39, 'capriolo'), (40, 'colombaccio'), (41, 'cinghiale'), (42, 'camaleonte'), (43, 'cobra'), " +
//						"(44, 'coccodrillo'), (45, 'castoro'), (46, 'civetta'), (47, 'coyote'), (48, 'colibrì'), " +
//						"(49, 'condor'), (50, 'canguro'), (51, 'cigno'), (52, 'chea'), (53, 'colomba'), (54, 'chea'), " +
//						"(55, 'coccinella'), (56, 'chiocciola'), (57, 'cavalletta'), (58, 'cicala'), (59, 'donnola'), " +
//						"(60, 'dingo'), (61, 'daino'), (62, 'dalmata'), (63, 'delfino'), (64, 'dentice'), " +
//						"(65, 'dinosauro'), (66, 'drago'), (67, 'elefante'), (68, 'euripiga'), (69, 'emu'), " +
//						"(70, 'ermellino'), (71, 'fagiano'), (72, 'faina'), (73, 'falco'), (74, 'facocero'), " +
//						"(75, 'formichiere'), (76, 'formica'), (77, 'farfalla'), (78, 'faraona'), (79, 'foca'), " +
//						"(80, 'fringuello'), (81, 'granchio'), (82, 'ghepardo'), (83, 'gazza'), (84, 'ghiandaia'), " +
//						"(85, 'gufo'), (86, 'gruccione'), (87, 'gatto'), (88, 'gallo'), (89, 'gallina'), " +
//						"(90, 'guereza'), (91, 'gnu'), (92, 'gorilla'), (93, 'giraffa'), (94, 'giaguaro'), " +
//						"(95, 'grillotalpa'), (96, 'grillo'), (97, 'gabbiano'), (98, 'gambero'), (99, 'gazzella'), " +
//						"(100, 'ghiro'), (101, 'griffone'), (102, 'istrice'), (103, 'iena'), (104, 'impala'), " +
//						"(105, 'ippopotamo'), (106, 'ignicolore'), (107, 'iguana'), (108, 'luccio'), (109, 'limulo'), " +
//						"(110, 'lumaca'), (111, 'leopardo'), (112, 'lepre'), (113, 'lupo'), (114, 'leone'), " +
//						"(115, 'lince'), (116, 'lama'), (117, 'lucciola'), (118, 'libellula'), (119, 'merluzzo'), " +
//						"(120, 'murena'), (121, 'muggine'), (122, 'medusa'), (123, 'martora'), (124, 'marabù'), " +
//						"(125, 'mandrillo'), (126, 'moffetta'), (127, 'maggiolino'), (128, 'malmignatta'), " +
//						"(129, 'macaone'), (130, 'mantide religiosa'), (131, 'nasello'), (132, 'nautilo'), " +
//						"(133, 'nandù'), (134, 'numbato'), (135, 'necroforo'), (136, 'orata'), (137, 'orango'), " +
//						"(138, 'orso'), (139, 'otarda'), (140, 'okapia'), (141, 'oritteropo'), (142, 'oca'), " +
//						"(143, 'ocellotto'), (144, 'ornitorinco'), (145, 'otaria orsina'), (146, 'oplia cerulea'), " +
//						"(147, 'pirahna'), (148, 'polpo'), (149, 'pavone'), (150, 'pettazzurro'), (151, 'panda'), " +
//						"(152, 'picchio'), (153, 'pitone'), (154, 'procione'), (155, 'puma'), (156, 'pellicano'), " +
//						"(157, 'papagallo'), (158, 'petauro'), (159, 'paradisea'), (160, 'purpuriceno'), " +
//						"(161, 'punteruolo'), (162, 'quertzal'), (163, 'quaglia'), (164, 'rana'), (165, 'remora'), " +
//						"(166, 'rinoceronte'), (167, 'riccio'), (168, 'ratto'), (169, 'rigogolo'), (170, 'rampichino'), " +
//						"(171, 'ramarro'), (172, 'racoforo'), (173, 'rospo'), (174, 'spigola'), (175, 'scombro'), " +
//						"(176, 'sardina'), (177, 'sogliola'), (178, 'salmerino'), (179, 'salmone'), (180, 'storione'), " +
//						"(181, 'saccofaringe'), (182, 'seppia'), (183, 'scoiattolo'), (184, 'struzzo'), " +
//						"(185, 'salamandra'), (186, 'serpente'), (187, 'spatola'), (188, 'spizaeto'), (189, 'tapiro'), " +
//						"(190, 'scimmia'), (191, 'scorpione'), (192, 'sfinge'), (193, 'scarabeo'), (194, 'sicofante'), " +
//						"(195, 'scarabeo'), (196, 'tonno'), (197, 'triglia'), (198, 'trota'), (199, 'tigre'), " +
//						"(200, 'tasso'), (201, 'topo'), (202, 'taccola'), (203, 'turaco'), (204, 'tartaruga'), " +
//						"(205, 'tanagra'), (206, 'tamandua'), (207, 'trogone violaceo'), (208, 'tucano'), " +
//						"(209, 'usignolo'), (210, 'uccello'), (211, 'urania'), (212, 'volpe'), (213, 'vipera'), " +
//						"(214, 'vombato'), (215, 'vespa'), (216, 'vacca'), (217, 'volpino'), (218, 'zibetto'), " +
//						"(219, 'zigolo testa nera'), (220, 'zebra'), (221, 'zanzara'), (222, 'yak'), " +
//						"(223, 'yaguarondi'), (224, 'jacana africana'), (225, 'koala'), (226, 'kookaburra'), " +
//						"(227, 'kiwi'), (228, 'kagu')",
//				"drop table aggettivo if exists",
//				"create table aggettivo(id_aggettivo serial, nome varchar(20))",
//				"insert into aggettivo(id_aggettivo, nome) values (1, 'abile'), (2, 'abitudinario'), (3, 'accogliente'), " +
//						"(4, 'accomodante'), (5, 'addolorato'), (6, 'affascinante'), (7, 'affidabile'), " +
//						"(8, 'afflitto'), (9, 'aggressivo'), (10, 'agitato'), (11, 'allegro'), (12, 'alternativo'), " +
//						"(13, 'altruista'), (14, 'amabile'), (15, 'ambizioso'), (16, 'amichevole'), (17, 'amorevole'), " +
//						"(18, 'antipatico'), (19, 'apatico'), (20, 'appassionato'), (21, 'apprezzato'), (22, 'ardito'), " +
//						"(23, 'armonioso'), (24, 'arrendevole'), (25, 'asociale'), (26, 'astuto'), (27, 'attento'), " +
//						"(28, 'audace'), (29, 'avaro'), (30, 'avveduto'), (31, 'avventato'), (32, 'bello'), " +
//						"(33, 'bizzarro'), (34, 'bonario'), (35, 'bravo'), (36, 'brutto'), (37, 'bugiardo'), " +
//						"(38, 'buono'), (39, 'burbero'), (40, 'calmo'), (41, 'capace'), (42, 'caparbio'), (43, 'carino'), " +
//						"(44, 'carismatico'), (45, 'cattivo'), (46, 'chiacchierone'), (47, 'chiaro'), (48, 'cinico'), " +
//						"(49, 'codardo'), (50, 'coerente'), (51, 'collerico'), (52, 'colto'), (53, 'compassionevole'), " +
//						"(54, 'compiaciuto'), (55, 'comprensivo'), (56, 'compreso'), (57, 'comune'), (58, 'comune'), " +
//						"(59, 'concentrato'), (60, 'conciliante'), (61, 'conosciuto'), (62, 'contentabile'), " +
//						"(63, 'contento'), (64, 'contratto'), (65, 'copione'), (66, 'coraggioso'), (67, 'cordiale'), " +
//						"(68, 'corretto'), (69, 'cortese'), (70, 'costante'), (71, 'creativo'), (72, 'crudele'), " +
//						"(73, 'curioso'), (74, 'debole'), (75, 'deciso'), (76, 'determinato'), (77, 'diffidente'), " +
//						"(78, 'dimesso'), (79, 'dinamico'), (80, 'diplomatico'), (81, 'disattento'), (82, 'discreto'), " +
//						"(83, 'disfattista'), (84, 'disinteressato'), (85, 'disinvolto'), (86, 'disonesto'), " +
//						"(87, 'disordinato'), (88, 'disorganizzato'), (89, 'disponibile'), (90, 'disprezzato'), " +
//						"(91, 'distratto'), (92, 'disubbidiente'), (93, 'divertente'), (94, 'dolce'), (95, 'eccentrico'), " +
//						"(96, 'educato'), (97, 'egoista'), (98, 'elastico'), (99, 'elegante'), (100, 'emotivo'), " +
//						"(101, 'energico'), (102, 'enigmatico'), (103, 'entusiasta'), (104, 'equilibrato'), " +
//						"(105, 'esauriente'), (106, 'esigente'), (107, 'estroverso'), (108, 'falso'), " +
//						"(109, 'fastidioso'), (110, 'fedele'), (111, 'felice'), (112, 'fiacco'), (113, 'fidato'), " +
//						"(114, 'fiducioso'), (115, 'fiducioso'), (116, 'fifone'), (117, 'figo'), (118, 'forte'), " +
//						"(119, 'fortunato'), (120, 'forzuto'), (121, 'fragile'), (122, 'freddo'), (123, 'frigido'), " +
//						"(124, 'furbo'), (125, 'gagliardo'), (126, 'garbato'), (127, 'geloso'), (128, 'generoso'), " +
//						"(129, 'gentile'), (130, 'gioioso'), (131, 'gioviale'), (132, 'giusto'), (133, 'goffo'), " +
//						"(134, 'gradevole'), (135, 'grazioso'), (136, 'grezzo'), (137, 'idoneo'), (138, 'ignorante'), " +
//						"(139, 'immaturo'), (140, 'impacciato'), (141, 'impavido'), (142, 'impaziente'), " +
//						"(143, 'imperfetto'), (144, 'impertinente'), (145, 'imperturbabile'), (146, 'importante'), " +
//						"(147, 'impotente'), (148, 'impreparato'), (149, 'imprevedibile'), (150, 'imprudente'), " +
//						"(151, 'impulsivo'), (152, 'inaffidabile'), (153, 'incantevole'), (154, 'incapace'), " +
//						"(155, 'incerto'), (156, 'incoerente'), (157, 'incompreso'), (158, 'incontentabile'), " +
//						"(159, 'indeciso'), (160, 'indifferente'), (161, 'indisponibile'), (162, 'indulgente'), " +
//						"(163, 'inesperto'), (164, 'inetto'), (165, 'infedele'), (166, 'infelice'), (167, 'inferiore'), " +
//						"(168, 'ingegnoso'), (169, 'ingenuo'), (170, 'ingrato'), (171, 'inidoneo'), (172, 'innocuo'), " +
//						"(173, 'inospitale'), (174, 'insensato'), (175, 'insensibile'), (176, 'insicuro'), " +
//						"(177, 'insignificante'), (178, 'insoddisfatto'), (179, 'insolente'), (180, 'intelligente'), " +
//						"(181, 'interessante'), (182, 'interessato'), (183, 'intransigente'), (184, 'introverso'), " +
//						"(185, 'inutile'), (186, 'invadente'), (187, 'invidioso'), (188, 'irrazionale'), " +
//						"(189, 'irregolare'), (190, 'irrequieto'), (191, 'irresponsabile'), (192, 'istruito'), " +
//						"(193, 'laborioso'), (194, 'leale'), (195, 'lento'), (196, 'libero'), (197, 'litigioso'), " +
//						"(198, 'lontano'), (199, 'loquace'), (200, 'lunatico'), (201, 'magnanimo'), " +
//						"(202, 'maldestro'), (203, 'maleducato'), (204, 'malinconico'), (205, 'malvagio'), " +
//						"(206, 'maturo'), (207, 'memore'), (208, 'meschino'), (209, 'mesto'), (210, 'minchione'), " +
//						"(211, 'minuzioso'), (212, 'misericordioso'), (213, 'misterioso'), (214, 'mite'), " +
//						"(215, 'modesto'), (216, 'naturale'), (217, 'negativo'), (218, 'negligente'), (219, 'noioso'), " +
//						"(220, 'noncurante'), (221, 'normale'), (222, 'obbediente'), (223, 'occupato'), " +
//						"(224, 'odioso'), (225, 'onesto'), (226, 'operoso'), (227, 'ordinato'), (228, 'organizzato'), " +
//						"(229, 'orgoglioso'), (230, 'originale'), (231, 'orribile'), (232, 'oscuro'), " +
//						"(233, 'ossequioso'), (234, 'ostile'), (235, 'ottimista'), (236, 'ozioso'), (237, 'pacifico'), " +
//						"(238, 'partecipe'), (239, 'pauroso'), (240, 'paziente'), (241, 'pazzo'), (242, 'perdente'), " +
//						"(243, 'perfetto'), (244, 'pericoloso'), (245, 'perspicace'), (246, 'pessimista'), " +
//						"(247, 'piacevole'), (248, 'pigro'), (249, 'positivo'), (250, 'potente'), (251, 'povero'), " +
//						"(252, 'pratico'), (253, 'pregevole'), (254, 'premuroso'), (255, 'preparato'), " +
//						"(256, 'presuntuoso'), (257, 'prevedibile'), (258, 'profumato'), (259, 'propositivo'), " +
//						"(260, 'prudente'), (261, 'pulito'), (262, 'puntuale'), (263, 'puro'), (264, 'puzzolente'), " +
//						"(265, 'radioso'), (266, 'raffinato'), (267, 'raggiante'), (268, 'ragionevole'), " +
//						"(269, 'razionale'), (270, 'realista'), (271, 'regolare'), (272, 'repellente'), " +
//						"(273, 'responsabile'), (274, 'ribelle'), (275, 'ricco'), (276, 'riconoscente'), " +
//						"(277, 'riflessivo'), (278, 'rigido'), (279, 'rilassato'), (280, 'rinunciatario'), " +
//						"(281, 'ripugnante'), (282, 'riservato'), (283, 'rispettoso'), (284, 'ritardatario'), " +
//						"(285, 'romantico'), (286, 'rozzo'), (287, 'saccente'), (288, 'saggio'), (289, 'savio'), " +
//						"(290, 'sbagliato'), (291, 'scherzoso'), (292, 'scialbo'), (293, 'sconosciuto'), " +
//						"(294, 'sconsiderato'), (295, 'scontento'), (296, 'scontroso'), (297, 'scorbutico'), " +
//						"(298, 'scorretto'), (299, 'scortese'), (300, 'scortese'), (301, 'scriteriato'), " +
//						"(302, 'sdegnato'), (303, 'sedentario'), (304, 'semplice'), (305, 'sensato'), " +
//						"(306, 'sensibile'), (307, 'sereno'), (308, 'serioso'), (309, 'sfacciato'), (310, 'sfigato'), " +
//						"(311, 'sfortunato'), (312, 'sgarbato'), (313, 'sgradevole'), (314, 'sgraziato'), " +
//						"(315, 'sicuro'), (316, 'simpatico'), (317, 'sincero'), (318, 'sleale'), (319, 'smemorato'), " +
//						"(320, 'sociale'), (321, 'socievole'), (322, 'soddisfatto'), (323, 'sognatore'), " +
//						"(324, 'solitario'), (325, 'solito'), (326, 'spavaldo'), (327, 'spento'), (328, 'spietato'), " +
//						"(329, 'spigliato'), (330, 'splendido'), (331, 'sporco'), (332, 'sportivo'), " +
//						"(333, 'spregevole'), (334, 'sprezzante'), (335, 'squilibrato'), (336, 'stabile'), " +
//						"(337, 'statico'), (338, 'stimato'), (339, 'stonato'), (340, 'stordito'), (341, 'strano'), " +
//						"(342, 'straordinario'), (343, 'studioso'), (344, 'stupido'), (345, 'superficiale'), " +
//						"(346, 'superiore'), (347, 'sveglio'), (348, 'sventato'), (349, 'svergognato'), " +
//						"(350, 'svogliato'), (351, 'taciturno'), (352, 'taciturno'), (353, 'testardo'), " +
//						"(354, 'timido'), (355, 'timoroso'), (356, 'tollerante'), (357, 'traditore'), " +
//						"(358, 'tranquillo'), (359, 'trasandato'), (360, 'trepido'), (361, 'triste'), (362, 'turbato'), " +
//						"(363, 'ubbidiente'), (364, 'umile'), (365, 'unico'), (366, 'vagabondo'), (367, 'valoroso'), " +
//						"(368, 'vanitoso'), (369, 'veloce'), (370, 'vendicativo'), (371, 'vicino'), (372, 'vigliacco'), " +
//						"(373, 'vincente'), (374, 'violento'), (375, 'virtuoso'), (376, 'viziato'), (377, 'volenteroso'), " +
//						"(378, 'volubile')",
//				"drop table stanza if exists",
//				"create table stanza(id_stanza varchar(10) unique, nome varchar(200))",
//				"drop table tabella if exists",
//				"create table tabella(id_tabella varchar(50) unique, id_stanza varchar(50), sequenza varchar(80))",
//				"drop table numero_uscito if exists",
//				"create table numero_uscito(id_stanza varchar(50), numero number(3,0), data datetime)",
//				"drop table vincita if exists",
//				"create table vincita(id_stanza varchar(50), premio number(1,0), id_tabella varchar(50))",
//				"commit"
//		};
//
//		Arrays.asList(sqlStatements).forEach(sql -> {
//			jdbcTemplate.execute(sql);
//		});
//
//	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseServer() throws SQLException {
		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
	}
}
