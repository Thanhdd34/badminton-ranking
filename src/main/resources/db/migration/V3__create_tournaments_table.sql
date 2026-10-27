
CREATE TABLE tournaments (
                             id BIGSERIAL PRIMARY KEY,

                             name VARCHAR(150) NOT NULL,

                             location VARCHAR(150),

                             start_date DATE NOT NULL,

                             end_date DATE NOT NULL,

                             status VARCHAR(20) NOT NULL DEFAULT 'UPCOMING',

                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                             updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                             CONSTRAINT chk_tournaments_dates
                                 CHECK (end_date >= start_date),

                             CONSTRAINT chk_tournaments_status
                                 CHECK (status IN (
                                                   'UPCOMING',
                                                   'ONGOING',
                                                   'COMPLETED',
                                                   'CANCELLED'
                                     ))
);
