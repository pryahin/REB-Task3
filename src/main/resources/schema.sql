DROP TABLE IF EXISTS Shares CASCADE;

CREATE TABLE IF NOT EXISTS Shares (
  Owner      TEXT,
  Issuer     TEXT,
  Percentage REAL
);
