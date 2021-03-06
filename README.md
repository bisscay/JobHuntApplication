# JobHuntApplication
An online job marketplace, think Freelancer, Upwork or Gigster. 

Job requester posts their job descriptions to the marketplace and registered freelancer can offer to undertake the job. 

The application meets all functional and technical requirements and is evaluated against OWASP Top Ten Vulnerabilities.

## Functional Requirements
Access to the job marketplace is limited – you must provide an authentication scheme. 

Access rights are role based, where your system provides three roles: provider, freelancer and administrator.

###### Provider can perform the following tasks:
• Create a job description (job is marked “open”). A job description includes:

- Title

- Unique Job ID (should be generated by the system)

- Keywords

- Job description

- Payment offer

• List all job descriptions posted by the provider.

• Remove an open job description.

• View profiles of any freelancer that offered to undertake a job description.

• Accept a freelancer for a posted job description (assign the job to a freelancer that offered
to undertake the job). Once a freelancer has been accepted, the job will be marked as “closed”.

• Mark a job description as completed (when a freelancer has completed the job). Once a
job is closed, the “payment” will be assigned to the freelancer.

###### Freelancer can perform the following:

• Browse through all open job offers (offers that have not yet been assigned to a freelancer
and have not yet been completed).

• Search job offers by keyword (list all offers that include the specified keyword) and browse
through the search result.

• Search job offer by unique Job ID.

• Offer to undertake an open job description.

• Revoke an offer to undertake a job (only before the requester has accepted the
freelancer).

• Edit their profile - must contain at least name, Freelancer ID (unique, assigned by system),
list of skills and a message to job requestors (allow at least 500 characters).

• View the amount in their “payment” account.

###### Administrators can perform:
• Register freelancer to the database.
• Remove freelancer from the database.
• Register job provider to the database.
• Remove job provider from the database.
• Remove any job description from the system (in any state, i.e. “open”, “closed” or “completed”.

###### A logging facility:
• Every time a requestor accepts a freelancer or marks a job as closed a corresponding entry
is added to the log (either a log-file or database table).
• Every time a freelancer offers to undertake a job description a corresponding entry is
added to the log (either a log-file or database table).

## Technical Requirements
Your solution must implement all features using Java EE (EJB, entity classes, persistence API) and JSF/HTML only. 

Your solution must also have the following properites:

• All posted job descriptions are kept in a database.

• All users of the system (provider, freelancer, administrators) and their details must be kept in a database.

• You must use at least one Session Enterprise JavaBean (either stateless or stateful) that is remotely accessible.

• You must use Message Driven Beans for the logging facility.

• Your web interface must utilize:

- At least one RequestScoped managed bean.

- At least one SessionScoped managed bean.

- At least one composite component.

- At least one custom converter tag or one custom validator tag.

- JSF Templates

• Your application must be resilient to the following OWASP Top Ten risks:

- Injection

- Sensitive Data Exposure (Note: I do not expect you to setup certificates for HTTPS communication – 
to simplify this project it is ok to use plain HTTP. 
However, you should implement other features to avoid this risk).

- Cross-Site Scripting (XXS)

- Insufficient Logging and Monitoring

## TO DO:
- Use Remote Session EJB - EJBs default to local - you need @Remote for remote access 
  (Application invokes web pages on server, which then invoke EJBs on same server - this is not remote)
- Use MDB for logging
- Evaluate against XXE - Show where application accepts XML documents that could be injected.
- Evaluate against Insecure Deserialization Incomplete: Clarify where deserialized objects that could be exploited is used
