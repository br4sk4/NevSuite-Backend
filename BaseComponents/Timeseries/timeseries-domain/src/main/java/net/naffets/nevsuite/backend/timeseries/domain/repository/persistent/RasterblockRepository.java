package net.naffets.nevsuite.backend.timeseries.domain.repository.persistent;

import net.naffets.nevsuite.backend.timeseries.domain.entity.Rasterblock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author br4sk4
 * created on 25.10.2016
 */
@Repository
public interface RasterblockRepository extends JpaRepository<Rasterblock, String> {

    List<Rasterblock> findByTimeseriesIdentifier(String identifier);

}
