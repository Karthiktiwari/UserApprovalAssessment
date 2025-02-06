@Entity
@Table(name = "task_approvers")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskApprover {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(name = "task_id")
    private UUID taskId;
    
    @Column(name = "approver_id")
    private UUID approverId;
    
    @Enumerated(EnumType.STRING)
    private TaskApproverStatus status;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}