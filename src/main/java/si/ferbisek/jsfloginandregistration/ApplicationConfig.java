package si.ferbisek.jsfloginandregistration;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.annotation.FacesConfig;
import jakarta.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import jakarta.security.enterprise.authentication.mechanism.http.LoginToContinue;
import jakarta.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;

@CustomFormAuthenticationMechanismDefinition(
        loginToContinue = @LoginToContinue(
                loginPage = "/login.xhtml",
                useForwardToLogin = false,
                errorPage = ""
        )
)
@DatabaseIdentityStoreDefinition(
        dataSourceLookup = "java:jboss/datasources/CredentialsDS",
        callerQuery = "SELECT password FROM users WHERE username = ?",
        groupsQuery = "SELECT group_name FROM user_groups WHERE username = ?"
)

/*
This defines an in memory H2 database
No need to define it in persistence.xml
Cannot use ExampleDS is already defined in WildFly standalone.xml
* */
@DataSourceDefinition(
        name = "java:jboss/datasources/CredentialsDS",
        className = "org.h2.jdbcx.JdbcDataSource",
        url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE"
)
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {
}
