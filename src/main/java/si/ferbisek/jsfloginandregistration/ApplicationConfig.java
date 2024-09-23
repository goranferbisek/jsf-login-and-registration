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
        dataSourceLookup = "java:app/MyDataSource",
        callerQuery = "SELECT password FROM users WHERE username = ?",
        groupsQuery = "SELECT group_name FROM user_groups WHERE username = ?"
)
@DataSourceDefinition(
        name = "java:app/MyDataSource",
        className = "org.h2.jdbcx.JdbcDataSource",
        url = "jdbc:h2:~/test:MODE=LEGACY;DB_CLOSE_ON_EXIT=FALSE"
)
@FacesConfig
@ApplicationScoped
public class ApplicationConfig {
}
