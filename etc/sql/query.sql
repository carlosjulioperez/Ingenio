-- [query editor]

select * from ingenio.diatrabajo where id='ff80808172a5ebad0172c9078f1e0001';
select id, diatrabajo_id, descripcion from ingenio.stockfabrica where descripcion like 'JUNIT';
select * from ingenio.stockproceso where descripcion like 'JUNIT';
select * from ingenio.azucargranel where diatrabajo_id = 'ff80808172a5ebad0172c9078f1e0001';
select * from ingenio.mieles where diatrabajo_id = 'ff80808172a5ebad0172c9078f1e0001';

select * from ingenio.stockprocesodetalle3;
delete from ingenio.stockprocesodetalle3;
update ingenio.stockprocesodetalle3 set valor=null;

select i.descripcion, valor, d.indicador_id, i.campo, modificable
from ingenio.stockfabricadetalle73 d, ingenio.indicador i
where d.indicador_id = i.id
and stockfabrica_id='ff80808174df04070174df06e1f40000'
order by orden;

-- Stock Proceso
SELECT   orden, m.descripcion, m.campo, volumen1, volumen2
FROM     ingenio.stockprocesodetalle1 d, ingenio.material m
WHERE    d.material_id = m.id AND
		 stockproceso_id='ff80808174bd90450174bd9ee9310000'
ORDER BY orden;

--- detalle 3
SELECT   orden, indicador_id, i.campo, i.descripcion, valor
FROM     ingenio.stockprocesodetalle3 d, ingenio.indicador i
WHERE    d.indicador_id = i.id AND
		 stockproceso_id='ff80808174bd90450174bd9ee9310000'
ORDER BY orden;

--- detalle 3 de plantilla
SELECT   d.id, orden, indicador_id, i.descripcion
FROM     ingenio.stockprocesopdetalle3 d, ingenio.indicador i
WHERE    d.indicador_id = i.id AND
		 stockprocesop_codigo=1
ORDER BY orden;

--- INSERTAR A PARTIR DE OTRA TABLA
INSERT INTO ingenio.stockprocesodetalle3
	("id", orden, indicador_id, stockproceso_id)
	SELECT   id, orden, indicador_id, 'ff80808174bd90450174bd9ee9310000'
	FROM     ingenio.stockprocesopdetalle3
	WHERE    stockprocesop_codigo=1

select * from azucargranel where 

-- indicador.campo = 'tonMelProTotZaf'
update stockfabricadetalle73 set modificable = true 
where indicador_id = '88';
--where stockfabrica_id='ff80808174df04070174df06e1f40000' and indicador_id = '88';

select * from ingenio.indicador where descripcion like '%anterior%';
select * from ingenio.indicador where campo like '%TonAzu%'
select * from ingenio.indicador where id like '199'

select * from ingenio.indicador;
--delete from ingenio.indicador where id cin ('194','195','196','197','198','199','200','201','202','203')



-- [ant]

-- SELECT * FROM ingenio.paro;
-- SELECT * FROM ingenio.parodetalle;
-- SELECT * FROM ingenio.azucarcrudo;
-- SELECT * FROM ingenio.azucarcrudodetalle;

-- SELECT * FROM ingenio.material;

-- SELECT   orden, m.descripcion, m.campo
-- FROM     ingenio.stockprocesopdetalle d, ingenio.material m
-- WHERE    d.material_id = m.id
-- ORDER BY orden;

-- select * from ingenio.indicador where descripcion like 'Ton MF';

-- select * from ingenio.stockfabrica where descripcion like 'JUNIT';

-- select d.stockfabrica_id, i.descripcion, valor, i.campo, modificable
-- from ingenio.stockfabricadetalle17 d, ingenio.indicador i
-- where d.indicador_id = i.id
-- -- AND i.descripcion in ('H2','H3')
-- AND d.stockfabrica_id = 'ff80808175c9cc7b0175c9f6e9260013'
-- order by orden;

-- select i.descripcion, valor, i.campo, modificable
-- from ingenio.stockfabricadetalle1 d, ingenio.indicador i
-- where d.indicador_id = i.id
-- order by orden;

-- select i.descripcion, valor, i.campo, modificable
-- from ingenio.stockfabricadetalle73 d, ingenio.indicador i
-- where d.indicador_id = i.id
-- and stockfabrica_id='ff80808175b519450175b52dc8ae0000'
-- order by orden;

-- select * from ingenio.jugodetalle;

-- blc principal: ff80808174e4c5470174e4c9d1bf0002, diatrabajo_id: 
-- blc anterior : ff80808174d572200174d5b05424014d, diatrabajo_id: ff80808174d2eb750174d3096a920000

-- SELECT   orden, m.id, m.descripcion, m.campo, valor, cantidad, acumulado
-- FROM     ingenio.blcdetalle1 d, ingenio.material m
-- WHERE    d.material_id = m.id AND
--          d.blc_id='ff80808175cd9e8b0175cdbb8df70085'
-- ORDER BY orden;

--
-- SELECT   orden, m.id, m.descripcion, m.campo, valor
-- FROM     ingenio.blcdetalle5 d, ingenio.material m
-- WHERE    d.material_id = m.id AND
--          d.blc_id='ff808081751f1f7401751ffc8747006c'
-- ORDER BY orden;

-- -- SELECT   orden, i.id, i.descripcion, i.campo, unidades, acumulado, totalzafra 
-- SELECT   orden, i.id, i.descripcion, i.campo, unidades
-- FROM     ingenio.blcdetalle12 d, ingenio.indicador i
-- WHERE    d.indicador_id = i.id AND
--          d.blc_id='ff808081751a20d701751a2c662b0000'
-- ORDER BY orden;

-- SELECT   orden, i.id, i.descripcion, i.campo, unidades, acumulado, totalzafra 
-- SELECT   orden, i.id, i.descripcion, i.campo, unidades
-- FROM     ingenio.blcdetalle13 d, ingenio.indicador i
-- WHERE    d.indicador_id = i.id AND
--          d.blc_id='ff808081752f740c01752f7dbb1b0001'
-- ORDER BY orden;
--
-- SELECT   orden, m.id, m.descripcion, m.campo, d.azucarreductor
-- FROM     ingenio.blcdetalle14 d, ingenio.material m
-- WHERE    d.material_id = m.id AND
--          d.blc_id='ff808081754ce18301754ce27c110000'
-- ORDER BY orden;

-- SELECT   orden, m.id, m.descripcion, m.campo, d.brix
-- FROM     ingenio.blcdetalle16 d, ingenio.material m
-- WHERE    d.material_id = m.id AND
--          d.blc_id='ff808081754ce18301754ce27c110000'
-- ORDER BY orden;

-- SELECT   orden, descripcion
-- FROM     ingenio.blccenicanadetalle
-- WHERE    blccenicana_id='ff808081751f164301751f17611a0000'
-- ORDER BY orden;

-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 0, acumulado = 0 WHERE "id" = 'ff80808174d572200174d5a378e6010c';

-- Para usar en razorsql
-- WHERE blc_id='ff80808174d572200174d5a3721d010b' ORDER BY orden
-- SELECT   *
-- 		 from ingenio.blcdetalle1 d, ingenio.blc c
-- WHERE	 d.blc_id = c.id
-- 		 AND c.diatrabajo_id='ff80808174d2eb750174d3096a920000'
-- ORDER BY orden

-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 7807.45 WHERE "id" = 'ff80808174d572200174d5b05a440150';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 2114.54 WHERE "id" = 'ff80808174d572200174d5b05a440151';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 216.01 WHERE "id" = 'ff80808174d572200174d5b05a450154';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 11401.34 WHERE "id" = 'ff80808174d572200174d5b05a450155';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 7362.48 WHERE "id" = 'ff80808174d572200174d5b05a450156';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 7672.95 WHERE "id" = 'ff80808174d572200174d5b05a450157';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 1719.97 WHERE "id" = 'ff80808174d572200174d5b05a450158';
-- UPDATE ingenio.ingenio.blcdetalle1 SET valor = 1435.00 WHERE "id" = 'ff80808174d572200174d5b05a45015a';

select * from diatrabajo;
