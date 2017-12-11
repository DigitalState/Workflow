# Camunda
Camunda-BPM Microservice

Camunda BPM is a BPM Engine that executes BPMN, DMN, and CMMN models.
The DigitalState platform leverages Camunda across multiple microservices.  The BPM engine provides web service orchestration capabilities and typical workflows that map directly to how a business operates.

# Screenshots

## Camunda Cockpit:

Camunda is the primary UI that the DigitalState platform uses for Camunda Process Instance overview:

![Process Dashboard](./docs/resources/Process-Dashboard.png)

![Process List](./docs/resources/Process-List.png)

![Process Instance List](./docs/resources/Process-Instance-List.png)

![Process Instance Inspector](./docs/resources/Process-Instance-Inspector.png)

![Process Instance Inspector Task Selected](./docs/resources/Process-Instance-Inspector-Task-Selected.png)



# Install
Run `docker-compose up`

Then browser to: [http://localhost:8055/camunda/app/welcome]()

# Camunda Version

DigitalState maintains the Camunda engine as a standalone engine deployment which operates as a docker based microservice.

The current version of Camunda BPM that is supported by DigitalState is Camunda 7.8 Tomcat.

## Defaults

The Community edition of the DigitalState platform is deployed with the Community edition of Camunda-BPM plus some additional configurations:

1. JSoup library is included.
1. `deployment-aware=false` is set in the engine's configuration.

DigitalState's community edition does not make modifications to Camunda's Docker Default install for default example deployments, users, groups, etc.

## SECURITY

DigitalState's community edition provides Camunda-BPM with a un-protected Rest API.  This means that Camunda's Rest API is not protected by JWT or Basic Auth.  Please see the DigitalState Enterprise version for these addtional configurations.


# Process Project Template

When working with large projects, it is a typical requirement to provide automated deployments of all code and related files.  The [Process Project Template](https://github.com/DigitalState/ProcessProjectTemplate) provides a SCM template for working with BPMN, DMN, and CMMN files, related and supporting scripts, and additional configurations and documentation.

The template provides:
1. SCM best practices
1. Optional Jenkins File for automated deployment using Camunda's Rest API
1. Repeatable and Predictable configurations
1. Multi-Environment configuration support (dev, stage, prod, etc)
1. Helper Scripts for common scripting activities.

# Camunda Forum

DigitalState strongly encourages that developers and business staff join the Camunda-BPM Forum: http://forum.camunda.org. The forum is filled with valuable examples and support resources, and developers and business staff should be encouraged to openly ask questions, discuss, and support other users.




