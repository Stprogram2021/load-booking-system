package com.example.loadbooking.dto;

import com.example.loadbooking.model.BookingStatus;
import jakarta.validation.constraints.*;
import java.sql.Timestamp;
import java.util.UUID;

public class BookingDTO {

    private UUID id;

    @NotNull(message = "Load ID is required")
    private UUID loadId;

    @NotBlank(message = "Transporter ID cannot be blank")
    private String transporterId;

    @Positive(message = "Proposed rate must be positive")
    private double proposedRate;

    @Size(max = 255, message = "Comment cannot exceed 255 characters")
    private String comment;

    private BookingStatus status;

    @NotNull(message = "Requested date/time is required")
    private Timestamp requestedAt;

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getLoadId() {
        return loadId;
    }

    public void setLoadId(UUID loadId) {
        this.loadId = loadId;
    }

    public String getTransporterId() {
        return transporterId;
    }

    public void setTransporterId(String transporterId) {
        this.transporterId = transporterId;
    }

    public double getProposedRate() {
        return proposedRate;
    }

    public void setProposedRate(double proposedRate) {
        this.proposedRate = proposedRate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Timestamp getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Timestamp requestedAt) {
        this.requestedAt = requestedAt;
    }
}
