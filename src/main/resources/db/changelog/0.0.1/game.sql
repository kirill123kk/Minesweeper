create table if not exists game
(
    id                         varchar not null
    primary key,
    width                      int not null,
    height                     int not null,
    mines_count                int  not null,
    field_view                 varchar not null,
    field                      varchar not null,
    completed                  boolean not null
    );