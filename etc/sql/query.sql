-- SELECT * FROM ingenio.paro;
-- SELECT * FROM ingenio.parodetalle;
-- SELECT * FROM ingenio.azucarcrudo;
-- SELECT * FROM ingenio.azucarcrudodetalle;

SELECT * FROM ingenio.material;

select m.descripcion, m.campo
from ingenio.blcpdetalle6 d, ingenio.material m
where d.material_id = m.id
