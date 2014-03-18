CREATE TABLE JobSeekers (
  EmailAddress VARCHAR (255) NOT NULL,
  FirstName VARCHAR (255) NOT NULL,
  LastName VARCHAR (255) NOT NULL,
  CONSTRAINT JobSeekers_primary PRIMARY KEY ( EmailAddress )
)