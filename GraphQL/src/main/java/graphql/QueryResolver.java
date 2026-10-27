
package graphql;

// Import the business classes that contain the methods for managing data
import business.ModuleBusiness;
import business.UniteEnseignementBusiness;

// Import the interface required for a GraphQL query resolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

// Import the entity classes returned by our queries
import entities.Module;
import entities.UniteEnseignement;

import java.util.List;

// This class defines all GraphQL queries used to retrieve data
public class QueryResolver implements GraphQLQueryResolver {

    // Helper used to retrieve and manage Teaching Units (UE)
    private UniteEnseignementBusiness ueHelper;

    // Helper used to retrieve and manage Modules
    private ModuleBusiness moduleHelper;

    // Constructor: initializes the two business classes
    public QueryResolver() {
        ueHelper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    // ===== Queries for Unités d'Enseignement =====

    // Returns the list of all Teaching Units
    public List<UniteEnseignement> allUEs() {
        return ueHelper.getListeUE();
    }

    // Returns the Teaching Unit corresponding to a specific code
    // Returns null if no Teaching Unit has this code
    public UniteEnseignement ueByCode(int code) {
        return ueHelper.getUEByCode(code);
    }

    // Returns all Teaching Units belonging to a specified domain
    public List<UniteEnseignement> uesByDomaine(String domaine) {
        return ueHelper.getUEByDomaine(domaine);
    }

    // Returns all Teaching Units belonging to a specified semester
    public List<UniteEnseignement> uesBySemestre(int semestre) {
        return ueHelper.getUEBySemestre(semestre);
    }

    // ===== Queries for Modules =====

    // Returns the list of all Modules
    public List<Module> allModules() {
        return moduleHelper.getAllModules();
    }

    // Returns the Module corresponding to a specific matricule
    // Returns null if no Module has this matricule
    public Module moduleByMatricule(String matricule) {
        return moduleHelper.getModuleByMatricule(matricule);
    }

    // Returns all Modules having the specified type
    public List<Module> modulesByType(Module.TypeModule type) {
        return moduleHelper.getModulesByType(type);
    }
}
