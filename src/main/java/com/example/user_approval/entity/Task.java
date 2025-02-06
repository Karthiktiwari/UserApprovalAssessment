@Entity
@Table(name = "tasks")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    
    @Column(nullable = false)
    private String title;
    
    private String description;
    
    @Column(name = "creator_id", nullable = false)
    private UUID creatorId;
    
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    
    @Column(name = "required_approvals")
    private Integer requiredApprovals;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}