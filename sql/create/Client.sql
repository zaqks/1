USE el_meyloud_RE;

CREATE TABLE Clients(
    id int,
    
    name varchar(64),
    surname varchar(64),
    nin varchar(11),

    phonenum varchar(13),
    email varchar(64),

    ccp varchar(16),
    ccp_key varchar(2),
    rip varchar(16),

    x float,
    y float,

    sells boolean
);