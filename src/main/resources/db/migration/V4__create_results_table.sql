
CREATE TABLE results (
                         id BIGSERIAL PRIMARY KEY,

                         athlete_id BIGINT NOT NULL,
                         tournament_id BIGINT NOT NULL,

                         event_type VARCHAR(30) NOT NULL,
                         medal VARCHAR(10),

                         points INTEGER NOT NULL DEFAULT 0,

                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT fk_results_athlete
                             FOREIGN KEY (athlete_id)
                                 REFERENCES athletes(id)
                                 ON DELETE RESTRICT,

                         CONSTRAINT fk_results_tournament
                             FOREIGN KEY (tournament_id)
                                 REFERENCES tournaments(id)
                                 ON DELETE RESTRICT,

                         CONSTRAINT uq_results_athlete_tournament_event
                             UNIQUE (athlete_id, tournament_id, event_type),

                         CONSTRAINT chk_results_points
                             CHECK (points >= 0),

                         CONSTRAINT chk_results_event_type
                             CHECK (event_type IN (
                                                   'MENS_SINGLES',
                                                   'WOMENS_SINGLES',
                                                   'MENS_DOUBLES',
                                                   'WOMENS_DOUBLES',
                                                   'MIXED_DOUBLES'
                                 )),

                         CONSTRAINT chk_results_medal
                             CHECK (
                                 medal IS NULL OR
                                 medal IN ('GOLD', 'SILVER', 'BRONZE')
                                 )
);

CREATE INDEX idx_results_tournament_id
    ON results(tournament_id);
