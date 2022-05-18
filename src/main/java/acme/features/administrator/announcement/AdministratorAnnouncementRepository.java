package acme.features.administrator.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcements.Announcement;
import acme.entities.systemConfiguration.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAnnouncementRepository extends AbstractRepository {

	@Query("select a from Announcement a where a.id = :id")
	Announcement findOneAnnouncementById(int id);

	@Query("select a from Announcement a where a.creationDate >= :deadline")
	Collection<Announcement> findRecentAnnouncements(Date deadline);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select a from Announcement a where a.creationDate between '1900/01/01' and '1900/01/31'")
	Collection<Announcement> findAnnouncementsToPatch();

}
