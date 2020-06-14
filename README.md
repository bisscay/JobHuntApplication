# JobHuntApplication

TO DO:
- Use Remote Session EJB - EJBs default to local - you need @Remote for remote access 
  (Application invokes web pages on server, which then invoke EJBs on same server - this is not remote)
- Use MDB for logging
- Evaluate against XXE - Show where application accepts XML documents that could be injected.
- Evaluate against Insecure Deserialization Incomplete: Clarify where deserialized objects that could be exploited is used
