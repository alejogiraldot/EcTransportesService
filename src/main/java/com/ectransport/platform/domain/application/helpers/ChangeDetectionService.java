package com.ectransport.platform.domain.application.helpers;

import com.ectransport.platform.infrastructure.entity.ServiceOrderEntity;
import com.ectransport.platform.infrastructure.entity.ServiceRequirement;
import com.ectransport.platform.infrastructure.repository.ServiceRequirementJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChangeDetectionService {

  private final ObjectMapper objectMapper;
  private final ChangeEnrichmentService changeEnrichmentService;

  public ChangeDetectionService(
      ObjectMapper objectMapper,
      ChangeEnrichmentService changeEnrichmentService) { // NUEVO
    this.objectMapper = objectMapper;
    this.changeEnrichmentService = changeEnrichmentService;
  }

  public Map<String, Map<String, Object>> detectServiceChanges(
      ServiceOrderEntity oldEntity,
      ServiceOrderEntity newEntity,
      List<Integer> oldRequirements, // NUEVO
      List<Integer> newRequirements) { // RENOMBRADO

    Map<String, Map<String, Object>> changes = new HashMap<>();

    compareField(changes, "plate", oldEntity.getPlate(), newEntity.getPlate());
    compareField(changes, "origin", oldEntity.getOrigin(), newEntity.getOrigin());
    compareField(changes, "destination", oldEntity.getDestination(), newEntity.getDestination());
    compareField(changes, "voucher", oldEntity.getVoucher(), newEntity.getVoucher());
    compareField(changes, "userName", oldEntity.getUserName(), newEntity.getUserName());
    compareField(changes, "userEmail", oldEntity.getUserEmail(), newEntity.getUserEmail());
    compareField(changes, "userNumber", oldEntity.getUserNumber(), newEntity.getUserNumber());
    compareField(changes, "serviceDate", oldEntity.getServiceDate(), newEntity.getServiceDate());
    compareField(changes, "hourService", oldEntity.getHourService(), newEntity.getHourService());
    compareField(changes, "brandVehicle", oldEntity.getBranVehicle(), newEntity.getBranVehicle());
    compareField(changes, "peopleNumber", oldEntity.getPeopleNumber(), newEntity.getPeopleNumber());
    compareField(changes, "flightNumber", oldEntity.getFlightNumber(), newEntity.getFlightNumber());
    compareField(changes, "serviceAmount", oldEntity.getServiceAmmount(), newEntity.getServiceAmmount());
    compareField(changes, "methodOfPayment", oldEntity.getMethodOfPayment(), newEntity.getMethodOfPayment());
    compareField(changes, "observations", oldEntity.getObservations(), newEntity.getObservations());
    compareField(changes, "reference", oldEntity.getReference(), newEntity.getReference());
    compareField(changes, "fkDriver", oldEntity.getFkDriver(), newEntity.getFkDriver());
    compareField(changes, "fkTransport", oldEntity.getFkTransport(), newEntity.getFkTransport());
    compareField(changes, "fkClient", oldEntity.getFkClient(), newEntity.getFkClient());
    compareField(changes, "serviceType", oldEntity.getServiceType(), newEntity.getServiceType());

    compareRequirements(changes, oldRequirements, newRequirements);

    if (!changes.isEmpty()) {
      changeEnrichmentService.enrichChanges(changes);
    }

    return changes;
  }

  /**
   * NUEVO: Compara los requerimientos del servicio
   */
  private void compareRequirements(
      Map<String, Map<String, Object>> changes,
      List<Integer> oldRequirements,
      List<Integer> newRequirements) {

    List<Integer> oldReqSorted = oldRequirements != null
        ? new ArrayList<>(oldRequirements).stream().sorted().collect(Collectors.toList())
        : new ArrayList<>();

    List<Integer> newReqSorted = newRequirements != null
        ? new ArrayList<>(newRequirements).stream().sorted().collect(Collectors.toList())
        : new ArrayList<>();

    if (!oldReqSorted.equals(newReqSorted)) {
      Map<String, Object> change = new HashMap<>();
      change.put("initialValue", oldReqSorted.isEmpty() ? "Sin requerimientos" : oldReqSorted.toString());
      change.put("lastValue", newReqSorted.isEmpty() ? "Sin requerimientos" : newReqSorted.toString());
      change.put("fieldName", "Requerimientos");
      changes.put("requirements", change);
    }
  }

  private void compareField(
      Map<String, Map<String, Object>> changes,
      String fieldName,
      Object oldValue,
      Object newValue) {

    oldValue = normalizeValue(oldValue);
    newValue = normalizeValue(newValue);

    if (!areValuesEqual(oldValue, newValue)) {
      Map<String, Object> change = new HashMap<>();
      change.put("initialValue", oldValue);
      change.put("lastValue", newValue);
      change.put("fieldName", getFieldLabel(fieldName));
      changes.put(fieldName, change);
    }
  }

  private boolean areValuesEqual(Object oldValue, Object newValue) {
    if (oldValue instanceof BigDecimal && newValue instanceof BigDecimal) {
      return ((BigDecimal) oldValue).compareTo((BigDecimal) newValue) == 0;
    }
    return Objects.equals(oldValue, newValue);
  }

  private Object normalizeValue(Object value) {
    if (value == null) {
      return "";
    }
    if (value instanceof String && ((String) value).trim().isEmpty()) {
      return "";
    }
    if (value instanceof BigDecimal) {
      BigDecimal bd = (BigDecimal) value;
      return bd.stripTrailingZeros().toPlainString();
    }
    return value;
  }

  public String changesToJson(Map<String, Map<String, Object>> changes) {
    try {
      if (changes.isEmpty()) {
        return "{}";
      }
      return objectMapper.writeValueAsString(changes);
    } catch (Exception e) {
      throw new RuntimeException("Error convirtiendo cambios a JSON", e);
    }
  }

  private String getFieldLabel(String fieldName) {
    Map<String, String> labels = new HashMap<>();
    labels.put("plate", "Placa");
    labels.put("origin", "Origen");
    labels.put("destination", "Destino");
    labels.put("voucher", "Voucher");
    labels.put("userName", "Cliente");
    labels.put("userEmail", "Email");
    labels.put("userNumber", "Teléfono");
    labels.put("serviceDate", "Fecha del servicio");
    labels.put("hourService", "Hora");
    labels.put("brandVehicle", "Vehículo");
    labels.put("peopleNumber", "Número de pasajeros");
    labels.put("flightNumber", "Número de vuelo");
    labels.put("serviceAmount", "Monto del servicio");
    labels.put("methodOfPayment", "Método de pago");
    labels.put("observations", "Observaciones");
    labels.put("reference", "Referencia");
    labels.put("fkDriver", "Conductor");
    labels.put("fkTransport", "Tipo de transporte");
    labels.put("fkClient", "Cliente");
    labels.put("serviceType", "Tipo de servicio");
    return labels.getOrDefault(fieldName, fieldName);
  }
}