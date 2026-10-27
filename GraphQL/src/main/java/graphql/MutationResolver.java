
package graphql;

// Import the business classes that handle data operations
import business.ModuleBusiness;
import business.UniteEnseignementBusiness;

// Import the interface required for a GraphQL mutation resolver
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

// Import the entity classes used to create or update objects
import entities.Module;
import entities.UniteEnseignement;

// This class defines GraphQL mutations for adding, updating and deleting data
public class MutationResolver implements GraphQLMutationResolver {

    // Helper used for Teaching Unit operations
    private UniteEnseignementBusiness ueHelper;

    // Helper used for Module operations
    private ModuleBusiness moduleHelper;

    // Constructor: initializes the business classes
    public MutationResolver() {
        ueHelper = new UniteEnseignementBusiness();
        moduleHelper = new ModuleBusiness();
    }

    // ===== Mutations for Unités d'Enseignement =====

    // Adds a new Teaching Unit using the provided information
    public boolean addUniteEnseignement(
            int code,
            String domaine,
            String responsable,
            int credits,
            int semestre) {

        // Create a new Teaching Unit object with the received arguments
        UniteEnseignement ue = new UniteEnseignement(
                code, domaine, responsable, credits, semestre);

        // Call the business method to add the Teaching Unit
        // Returns true if the operation succeeds
        return ueHelper.addUniteEnseignement(ue);
    }

    // Updates an existing Teaching Unit identified by its code
    public boolean updateUniteEnseignement(
            int code,
            String domaine,
            String responsable,
            int credits,
            int semestre) {

        // Create an object containing the new Teaching Unit information
        UniteEnseignement updatedUE = new UniteEnseignement(
                code, domaine, responsable, credits, semestre);

        // Replace the existing Teaching Unit with the updated object
        // Returns false if the specified code does not exist
        return ueHelper.updateUniteEnseignement(code, updatedUE);
    }

    // Deletes a Teaching Unit identified by its code
    public boolean deleteUniteEnseignement(int code) {

        // Delegate the deletion to the business class
        // Returns true if the Teaching Unit was found and deleted
        return ueHelper.deleteUniteEnseignement(code);
    }

    // ===== Mutations for Modules =====

    // Adds a new Module and associates it with an existing Teaching Unit
    public boolean addModule(
            String matricule,
            String nom,
            int coefficient,
            int volumeHoraire,
            Module.TypeModule type,
            int codeUE) {

        // Find the Teaching Unit associated with the module
        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);

        // Do not add the Module if the Teaching Unit does not exist
        if (ue == null) {
            return false;
        }

        // Create the Module and associate it with the selected Teaching Unit
        Module module = new Module(
                matricule, nom, coefficient, volumeHoraire, type, ue);

        // Call the business method to add the Module
        return moduleHelper.addModule(module);
    }

    // Updates an existing Module identified by its matricule
    public boolean updateModule(
            String matricule,
            String nom,
            int coefficient,
            int volumeHoraire,
            Module.TypeModule type,
            int codeUE) {

        // Find the Teaching Unit to associate with the updated Module
        UniteEnseignement ue = ueHelper.getUEByCode(codeUE);

        // Stop the update if the specified Teaching Unit does not exist
        if (ue == null) {
            return false;
        }

        // Create a Module object containing the updated information
        Module updatedModule = new Module(
                matricule, nom, coefficient, volumeHoraire, type, ue);

        // Replace the existing Module using its matricule
        // Returns false if no Module with this matricule exists
        return moduleHelper.updateModule(matricule, updatedModule);
    }

    // Deletes a Module identified by its matricule
    public boolean deleteModule(String matricule) {

        // Delegate the deletion to the business class
        // Returns true if the Module was found and deleted
        return moduleHelper.deleteModule(matricule);
    }
}
