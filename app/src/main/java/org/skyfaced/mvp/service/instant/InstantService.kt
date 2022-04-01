package org.skyfaced.mvp.service.instant

import org.skyfaced.mvp.service.Service

interface InstantService : Service {
    val strings: List<String>
}